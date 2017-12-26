package com.liuxl.netty.IO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * Description:java IO
 *
 * @author liuxl
 * @date 2017/12/26
 */
public class PlainOioServer {

    public void server(int port) throws Exception {
        //绑定服务器端口
        final ServerSocket socket = new ServerSocket(port);
        try {
            while (true) {
                //接受连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                //创建新线程来处理连接
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            //给连接的客户端写消息
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            //一旦消息被写入和刷新，关闭连接
                            clientSocket.close();
                        } catch (IOException e) {
                            try {
                                clientSocket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    //启动线程以开始处理
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            socket.close();
        }
    }
}

