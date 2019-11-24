package com.ddh.thread;

/**
 * @ClassName PrintCharAndNumber
 * @Description: 3.写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z
 * @Author sea
 * @Version V1.0
 **/

public class PrintCharAndNumber {
    public static void main(String[] args) {
        PrintClass printClass = new PrintClass();
        Thread t1 = new Thread(printClass::printNum);
        Thread t2 = new Thread(printClass::printChar);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

class PrintClass {

    private static boolean flag = true;

    public synchronized void printNum() {

        for (int i = 1; i <= 52; i++) {
            if (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(i);
            System.out.print(++i);


//            System.out.println("当前线程是:　" + Thread.currentThread().getName() + "　the number print is: " + ++count);
//            System.out.println("当前线程是:　" + Thread.currentThread().getName() + "　the number print is: " + ++count);
            flag = !flag;
            this.notifyAll();
        }
    }

    public synchronized void printChar() {
        for (int i = 0; i < 26; i++) {

            if (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print((char) (65 + i));
//            System.out.println("当前线程是:　" + Thread.currentThread().getName() + "　the char print is: " + (char) (65 + i));
            flag = !flag;
            this.notifyAll();
        }
    }
}
