package com.ddh.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestAlternate {
    public static void main(String[] args) {
        Alternate alternate = new Alternate();
        System.out.println();
        final int count = 10;
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                alternate.printA();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                alternate.printB();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                alternate.printC();
            }
        }).start();
    }
}

class Alternate{
    private final Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private int number = 1;

    public void printA(){
        try {
            lock.lock();
            if (number != 1) {
                conditionA.await();
            }
            System.out.print("A");
            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        try {
            lock.lock();
            if (number != 2) {
                conditionB.await();
            }
            System.out.print("B");
            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        try {
            lock.lock();
            if (number != 3) {
                conditionC.await();
            }
            System.out.print("C");
            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}