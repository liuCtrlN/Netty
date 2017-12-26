package com.liuxl.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created with IntelliJ IDEA.
 * Description:客户端业务实现
 * channelActive():客户端连接服务器后被调用
 * channelRead0():从服务器接收到数据后调用
 * exceptionCaught():发生异常时被调用
 *
 * 在这里使用的是SimpleChannelInboundHandler而不使用ChannelInboundHandlerAdapter?
 * 主要原因是 ChannelInboundHandlerAdapter在处理完消息后需要负责释放资源。
 * 在这里将调用ByteBuf.release()来释放资源。
 * SimpleChannelInboundHandler会在 完成channelRead0后释放消息，
 * 这是通过Netty处理所有消息的ChannelHandler实现了ReferenceCounted接口达到的。
 * 为什么在服务器中不使用SimpleChannelInboundHandler呢?因为服务器要返回相同的消息给客户端，
 * 在服务器执行完成写操作之前不能释放调用读取到的消息，因为写操作是异步的，一旦写操作完成后，Netty中会自动释放消息
 * @author liuxl
 * @date 2017/12/26
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] req = new byte[msg.readableBytes()];
        msg.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Client received: " + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
