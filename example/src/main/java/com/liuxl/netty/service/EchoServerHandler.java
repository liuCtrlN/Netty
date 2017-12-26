package com.liuxl.netty.service;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created with IntelliJ IDEA.
 * Description:业务逻辑
 * Netty使用多个Channel Handler来达到对事件处理的分离，
 * 因为可以很容的添加、更新、删除业务逻辑处理handler。Handler很简单，
 * 它的每个方法都可以被重写，它的所有的方法中只有channelRead方法是必须要重写的。
 * @author liuxl
 * @date 2017/12/26
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server received: " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
