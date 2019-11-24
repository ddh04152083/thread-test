package com.ddh.thread.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ClassName Client
 * @Description: TODO
 * @Author sea
 * @Date 19-11-24 下午9:08
 * @Version V1.0
 **/
public class Client {
    public static void main(String[] args) {
        BufferedReader br ;
        String clientStr;
        try {
            Socket socket = new Socket("127.0.0.1", 6666);
            Send send = new Send(socket);
            Thread t1 = new Thread(send);
            t1.start();
            while (true) {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientStr = br.readLine();
                System.out.println("服务端: " + clientStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
