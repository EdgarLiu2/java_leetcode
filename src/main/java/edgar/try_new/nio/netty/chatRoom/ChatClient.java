package edgar.try_new.nio.netty.chatRoom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by liuzhao on 2022/7/6
 */
public class ChatClient {
    private final static Logger logger = LoggerFactory.getLogger(ChatClient.class);

    public static void main(String[] args) {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
//                    ChannelPipeline pipeline = ch.pipeline();
//                    // 解码器
//                    pipeline.addLast("decoder", new StringDecoder());
//                    // 编码器
//                    pipeline.addLast("encoder", new StringEncoder());
//                    // 业务处理handler
//                    pipeline.addLast(new ChatClientHandler());

                    ch.pipeline().addLast(
                            new StringDecoder(),    // 解码器
                            new StringEncoder(),    // 编码器
                            new ChatClientHandler() // 业务处理handler
                    );
                }
            });

            ChannelFuture f = bootstrap.connect(host, port).sync();

            // 客户端channel
            Channel channel = f.channel();
            logger.info("Connected to ChatServer. client={}", channel.localAddress());

            // 客户端输入信息，建立扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();

                // 通过channel发送到服务端
                channel.writeAndFlush(msg);
            }


//            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("ChatClient is interrupted.", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
