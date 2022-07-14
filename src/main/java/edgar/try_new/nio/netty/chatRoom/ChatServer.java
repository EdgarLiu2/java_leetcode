package edgar.try_new.nio.netty.chatRoom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzhao on 2022/7/6
 */
public class ChatServer {

    private final static Logger logger = LoggerFactory.getLogger(ChatServer.class);

    private final int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // bossGroup只处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workerGroup负责客户端业务逻辑处理
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup) // 绑定两个线程池
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
//                            ChannelPipeline pipeline = ch.pipeline();
//                            // 解码器
//                            pipeline.addLast("decoder", new StringDecoder());
//                            // 编码器
//                            pipeline.addLast("encoder", new StringEncoder());
//                            // 业务处理handler
//                            pipeline.addLast(new ChatServerHandler());

                            ch.pipeline().addLast(
                                    new StringDecoder(),    // 解码器
                                    new StringEncoder(),    // 编码器
                                    new ChatServerHandler() // 业务处理handler
                            );
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)    // server端连接队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器，绑定监听端口
            ChannelFuture f = bootstrap.bind(port).sync();
            logger.info("ChatServer is started");
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {

        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

        new ChatServer(port).run();
    }
}
