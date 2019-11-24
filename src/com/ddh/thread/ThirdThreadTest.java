package com.ddh.thread;

/**
 * @ClassName ThirdThread
 * @Description: 4.编写一个程序，启动三个线程，假设三个线程的ID分别是A，B，C；，每个线程将自己的ID值在屏幕上打印5遍，打印顺序是ABCABC...
 * @Author sea
 * @Version V1.0
 **/
public class ThirdThreadTest {

    public static void main(String[] args) throws InterruptedException {
        ThirdThread thirdThread = new ThirdThread();
        Thread t1 = new Thread(thirdThread::printA);
        Thread t2 = new Thread(thirdThread::printB);
        Thread t3 = new Thread(thirdThread::printC);
        t1.start();
//        t1.join();
        t2.start();
//        t2.join();
        t3.start();
//        t3.join();

    }
}

class ThirdThread {
    private volatile int flag = 1;

    public synchronized void printA() {
//        for (int i = 0; i < 5; i++) {
            if (flag != 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.print("A");
            flag = 2;
            this.notifyAll();
        }
//    }

    public synchronized void printB() {
//        for (int i = 0; i < 5; i++) {

            if (flag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("B");
            flag = 3;
            this.notifyAll();

        }
//    }

    public synchronized void printC() {
//        for (int i = 0; i < 5; i++) {

            if (flag != 3) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("C");
            flag = 1;
            this.notifyAll();
        }
//    }
}