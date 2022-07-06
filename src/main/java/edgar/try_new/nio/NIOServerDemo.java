package edgar.try_new.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by liuzhao on 2022/7/1
 */
public class NIOServerDemo {

    private final static Logger logger = LoggerFactory.getLogger(NIOServerDemo.class);

    public final static int SERVER_PORT = 9000;

    /**
     * 如果连接数太多的话，会有大量的无效遍历，假如有10000个连接，其中只有1000个连接有写数据，但是由于其他9000个连接并 没有断开，我们还是要每次轮询遍历一万次，其中有十分之九的遍历都是无效的
     */
    static void demo1() {

        try {
            // 创建NIO ServerSocketChannel，与BIO的serverSocket类似
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            // 绑定端口
            serverSocket.socket().bind(new InetSocketAddress(SERVER_PORT));
            // 设置ServerSocketChannel为非阻塞
            serverSocket.configureBlocking(false);
            logger.info("ServerSocketChannel is started");

            // 保存客户端连接
            List<SocketChannel> channelList = new ArrayList<>();

            while (true) {
                // 非阻塞模式accept方法不会阻塞，否则会阻塞
                // NIO的非阻塞是由操作系统内部实现的，底层调用了linux内核的accept函数
                SocketChannel clientSocket = serverSocket.accept();

                if (clientSocket != null) {
                    logger.info("ClientSocket is connected");

                    // 设置SocketChannel为非阻塞
                    clientSocket.configureBlocking(false);
                    // 保存客户端连接在List中
                    channelList.add(clientSocket);
                }

                if (!channelList.isEmpty()) {
                    // 遍历连接进行数据读取
                    Iterator<SocketChannel> iterator = channelList.iterator();
                    while (iterator.hasNext()) {
                        SocketChannel socket = iterator.next();

                        ByteBuffer dst = ByteBuffer.allocate(128);

                        // 非阻塞模式read方法不会阻塞，否则会阻塞
                        int len = socket.read(dst);

                        // 如果有数据，把数据打印出来
                        if (len > 0) {
                            logger.info("Read message from client: {}", new String(dst.array()));
                        } else if (len == -1){
                            // 如果客户端断开，把socket从集合中去掉
                            iterator.remove();
                            logger.info("ClientSocket is closed");
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("NIO internal error", e);
        }
    }

    /**
     * 引入多路复用器，减少无效遍历
     * NIO 有三大核心组件： Channel(通道)， Buffer(缓冲区)，Selector(多路复用器)
     *  1、channel 类似于流，每个 channel 对应一个 buffer缓冲区，buffer 底层就是个数组
     *  2、channel 会注册到 selector 上，由 selector 根据 channel 读写事件的发生将其交由某个空闲的线程处理
     *  3、NIO 的 Buffer 和 channel 都是既可以读也可以写
     */
    static void demo2() {

        try {
            // 创建NIO ServerSocketChannel，与BIO的serverSocket类似
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            // 绑定端口
            serverSocket.socket().bind(new InetSocketAddress(SERVER_PORT));
            // 设置ServerSocketChannel为非阻塞
            serverSocket.configureBlocking(false);
            // 打开Selector处理Channel，即创建epoll
            Selector selector = Selector.open();
            // 把ServerSocketChannel注册到selector上，并且selector对客户端accept连接操作感兴趣
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("ServerSocketChannel is started");


            while (true) {
                // 阻塞等待需要处理的事件发生
                selector.select();

                // 获取selector中注册的全部事件的 SelectionKey 实例
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                // 遍历SelectionKey对事件进行处理
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        // 如果是OP_ACCEPT事件，则进行连接获取和事件注册
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();

                        SocketChannel clientSocket = server.accept();
                        // 设置SocketChannel为非阻塞
                        clientSocket.configureBlocking(false);

                        // 这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                        clientSocket.register(selector, SelectionKey.OP_READ);

                        logger.info("ClientSocket is connected");
                    } else if (key.isReadable()) {
                        // 如果是OP_READ事件，则进行读取和打印
                        SocketChannel clientSocket = (SocketChannel) key.channel();

                        ByteBuffer dst = ByteBuffer.allocate(128);
                        // 非阻塞模式read方法不会阻塞，否则会阻塞
                        int len = clientSocket.read(dst);

                        // 如果有数据，把数据打印出来
                        if (len > 0) {
                            logger.info("Read message from client: {}", new String(dst.array()));
                        } else if (len == -1){
                            // 如果客户端断开，关闭Socket
                            clientSocket.close();
                            logger.info("ClientSocket is closed");
                        }
                    }

                    // 从事件集合里删除本次处理的key，防止下次select重复处理
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            logger.error("NIO internal error", e);
        }
    }

    public static void main(String[] args)  {
        demo1();
//        demo2();
    }
}
