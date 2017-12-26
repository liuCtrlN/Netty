package com.liuxl.netty.NIO;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * Description:Netty NIO
 *
 * @author liuxl
 * @date 2017/12/26
 */
public class NettyNioServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));
        // 事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 用来引导服务器配置
            ServerBootstrap b = new ServerBootstrap();
            // 使用NIO异步模式
            b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    // 指定ChannelInitializer初始化handlers
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 添加一个“入站”handler到ChannelPipeline
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    // 连接后，写消息到客户端，写完后便关闭连接
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            // 释放所有资源
            group.shutdownGracefully();
        }
    }
}
