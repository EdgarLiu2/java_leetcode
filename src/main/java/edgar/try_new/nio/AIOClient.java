package edgar.try_new.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * Created by liuzhao on 2022/7/11
 */
public class AIOClient {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel clientSocketChannel = AsynchronousSocketChannel.open();
        clientSocketChannel.connect(new InetSocketAddress("localhost", AIOServerDemo.SERVER_PORT)).get();
        clientSocketChannel.write(ByteBuffer.wrap("Hello AIO".getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Integer len = clientSocketChannel.read(buffer).get();
        if (len != 1) {
            System.out.println("客户端收到信息：" + new String(buffer.array(), 0, len));
        }
    }
}
