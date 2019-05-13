package com.example.demo.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/17 17:58
 */
public class GreetingClient {

    public static void main(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);
            System.out.println(client.getLocalSocketAddress());
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println(dataInputStream.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
