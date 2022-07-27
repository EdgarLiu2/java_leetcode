package edgar.try_new.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.baeldung.com/netty
 *
 * Created by liuzhao on 2022/7/5
 */
public class NettyServer {

    private final static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // bossGroup只处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workerGroup负责客户端业务逻辑处理
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            // Netty服务端对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup) // 绑定两个线程池
                    .channel(NioServerSocketChannel.class)  // 服务端通道的实现类
                    .option(ChannelOption.SO_BACKLOG, 1024)    // server端连接队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {    // 创建通道初始化对象
                            // define inbound and outbound handlers that will process requests and output in the correct order
                            ch.pipeline().addLast(new RequestDecoder(),
                                    new ResponseDataEncoder(),
                                    new ProcessingHandler());
                        }
                    });

            // 启动服务器，绑定监听端口
            ChannelFuture f = bootstrap.bind(port).sync();
            logger.info("Netty Server is started");
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {

        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

        new NettyServer(port).run();
    }
}
