package com.ddh.thread;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName PrintNumThread
 * @Description: 2.两个线程轮流打印数字，一直到100
 * @Author sea
 * @Version V1.0
 **/
public class PrintNumThread {


    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        Thread t1 = new Thread(printThread::print1);
        Thread t2 = new Thread(printThread::print2);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }

}

class PrintThread {

    private static boolean flag = true;

    private int count = 0;

    public synchronized void print1() {
        for (int i = 0; i < 50; i++) {
            if (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            commonCode();
        }

    }

    public void commonCode() {
        System.out.println("当前线程是:　" + Thread.currentThread().getName() + "　the number print is: " + ++count);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = !flag;
        //System.out.println(flag);
        this.notifyAll();
    }

    public synchronized void print2() {

        for (int i = 0; i < 50; i++) {
            if (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            commonCode();

        }
    }

}