package edgar.try_new.nio;

import edgar.util.SleepUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by liuzhao on 2022/7/10
 */
public class AIOServerDemo {

    private final static Logger logger = LoggerFactory.getLogger(AIOServerDemo.class);

    public final static int SERVER_PORT = 9000;

    static void demo1() {
        try {
            AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(SERVER_PORT));
            serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                    try {
                        logger.info("Client={}", socketChannel.getRemoteAddress());

                        // 接收客户端连接
                        serverSocketChannel.accept(attachment, this);

                        // 从客户端读数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                buffer.flip();
                                String msg = new String(buffer.array(), 0, result);
                                logger.info("msg={}", msg);

                                socketChannel.write(ByteBuffer.wrap(("Echo Client: " + msg).getBytes()));
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                logger.error("Exception from socketChannel.read()", exc);
                            }
                        });

                    } catch (IOException e) {
                        logger.error("Exception from CompletionHandler.completed()", e);
                    }


                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    logger.error("Exception from serverSocketChannel.accept()", exc);
                }
            });
        } catch (IOException e) {
            logger.error("ServerSocket fail to listen", e);
        }

        logger.info("AIOServerDemo is ready for new connection.");
        SleepUtil.secondSleep(10);
    }

    public static void main(String[] args) {
        demo1();
    }
}
