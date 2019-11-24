package com.ddh.thread.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Send
 * @Description: TODO
 * @Author sea
 * @Date 19-11-24 下午8:47
 * @Version V1.0
 **/
public class Send implements Runnable {
    private Socket socket;

    public Send(Socket socket) {
        this.socket = socket;
    }

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        PrintWriter out = null;

        try {
            while (true) {
                out = new PrintWriter(socket.getOutputStream(), true);
                String str = scanner.next();
                out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }
}
