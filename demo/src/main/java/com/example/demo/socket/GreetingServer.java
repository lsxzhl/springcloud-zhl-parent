package com.example.demo.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/17 17:50
 */
public class GreetingServer extends Thread {

    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    @Override
    public void run(){
        while (true){
            try {
                System.out.println("等待远程连接：" + serverSocket.getLocalPort());
                Socket server = serverSocket.accept();
                System.out.println("等待远程连接：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println("谢谢连接：" + server.getLocalSocketAddress() );
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try {
            Thread t = new GreetingServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
