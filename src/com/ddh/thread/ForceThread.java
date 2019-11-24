package com.ddh.thread;

/**
 * @ClassName ForthObject
 * @Description: 1.现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 * @Author sea
 * @Version V1.0
 **/
public class ForceThread {
    public static void main(String[] args) {
        //现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
        GetThread t1 = new GetThread("t1");
        GetThread t2 = new GetThread("t2");
        GetThread t3 = new GetThread("t3");
        t1.start();
        try {
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("中断异常!");
        }


    }

}

class GetThread extends Thread {
    public GetThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("当前线程是: " + this.getName());
    }
}