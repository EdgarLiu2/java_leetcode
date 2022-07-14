package edgar.try_new.nio.netty.chatRoom;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuzhao on 2022/7/6
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    private final static Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // GlobalEventExecutor.INSTANCE 是全局的事件执行器
    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * channel 处于就绪状态，有新连接，提升上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();

        // 新用户加入聊天的消息
        String msg = String.format("%s [Client online] client=%s active_users=%d", sdf.format(new Date()), channel.remoteAddress(), channelGroup.size() + 1);
        logger.info(msg);

        // 将新用户上线的消息推送给其它客户端，该方法会遍历所有channel并发送消息
        channelGroup.writeAndFlush(msg + "\n");

        // 将新channel加入到channelGroup
        channelGroup.add(channel);
    }

    /**
     * channel 处于不活动状态时，提示离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();

        // 用户离开聊天的消息
        String msg = String.format("[Client offline] client=%s active_users=%d", channel.remoteAddress(), channelGroup.size());
        logger.info(msg);

        // 将新用户上线的消息推送给其它客户端，该方法会遍历所有channel并发送消息
        channelGroup.writeAndFlush(msg + "\n");
    }


    /**
     * 读消息
     *
     * @param ctx           the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *                      belongs to
     * @param msg           the message to handle
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        // 遍历channelGroup，给当前所有活动channel发消息
        channelGroup.forEach(ch -> {
            String message;

            if (channel != ch) {
                message = String.format("[From other] client=%s msg=%s\n", channel.remoteAddress(), msg);
            } else {
                message = String.format("[From me] client=%s msg=%s\n", ch.remoteAddress(), msg);
            }

            ch.writeAndFlush(message);
        });
    }
}
