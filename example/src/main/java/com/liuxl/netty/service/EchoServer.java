package com.liuxl.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created with IntelliJ IDEA.
 * Description:例子
 * 创建ServerBootstrap实例来引导绑定和启动服务器
 * 创建NioEventLoopGroup对象来处理事件，如接受新连接、接收数据、写数据等等
 * 指定InetSocketAddress，服务器监听此端口
 * 设置childHandler执行所有的连接请求
 * 都设置完毕了，最后调用ServerBootstrap.bind() 方法来绑定服务器
 * @author liuxl
 * @date 2017/12/26
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap实例
            ServerBootstrap b = new ServerBootstrap();
            //指定NIO传输，本地套接字地址
            //添加处理程序到通道管道
            b.group(group).channel(NioServerSocketChannel.class).localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定服务器，等待服务器关闭，并释放资源
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + "started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(65535).start();
    }
}
