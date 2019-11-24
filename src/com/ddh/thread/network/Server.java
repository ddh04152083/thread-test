package com.ddh.thread.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Description: TODO
 * @Author sea
 * @Date 19-11-24 下午8:39
 * @Version V1.0
 **/
public class Server {

    public static void main(String[] args) {
        BufferedReader br;
        String clientStr;
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket;
            System.out.println("服务器启动....");
            socket = serverSocket.accept();
            System.out.println("有客户端发送请求");
            Send send = new Send(socket);

            Thread t1 = new Thread(send);
            t1.start();
            while (true) {
                // 获取客户端通信的数据
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                clientStr = br.readLine();
                System.out.println("客户端: " + clientStr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
