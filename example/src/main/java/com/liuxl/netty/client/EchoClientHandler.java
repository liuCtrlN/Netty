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
 * @author liuxl
 * @date 2017/12/26
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + ByteBufUtil.hexDump(msg.readBytes(msg.readableBytes())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
