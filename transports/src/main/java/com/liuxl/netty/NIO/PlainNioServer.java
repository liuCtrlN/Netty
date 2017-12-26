package com.liuxl.netty.NIO;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * Description:NIO java
 *
 * @author liuxl
 * @date 2017/12/26
 */
public class PlainNioServer {

    public void server(int port) throws Exception {
        System.out.println("Listening for connections on port " + port);
        //打开处理通道的选择器
        Selector selector = Selector.open();
        //打开服务器socket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //get ServerSocket
        ServerSocket serverSocket = serverChannel.socket();
        //绑定服务器端口
        serverSocket.bind(new InetSocketAddress(port));
        //设置为非阻塞
        serverChannel.configureBlocking(false);
        //注册ServerSocket到选择器，并指定它对新接受的客户机serverChannel感兴趣。注册(选择器,SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        while (true) {
            //等待为流程准备的新事件。这会阻塞直到发生什么事情
            int n = selector.select();
            if (n > 0) {
                //获取接收到事件的所有SelectionKey实例
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    try {
                        //检查事件是否因为新客户准备接受
                        if (key.isAcceptable()) {

                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel client = server.accept();
                            System.out.println("Accepted connection from " + client);
                            client.configureBlocking(false);
                            //接受客户端并将其注册到选择器
                            client.register(selector, SelectionKey.OP_WRITE, msg.duplicate());

                        }
                        //检查事件是否准备写入数据socket
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer buff = (ByteBuffer) key.attachment();
                            //写数据到连接的客户端
                            while (buff.hasRemaining()) {
                                if (client.write(buff) == 0) {
                                    break;
                                }
                            }
                            client.close();
                        }
                    } catch (Exception e) {
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        }
    }
}
