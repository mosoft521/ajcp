package com.gmail.mosoft521.ajcp.ch01;

/**
 * 死锁例子
 */
public class DeadLockDemo {

    /**
     * A锁
     */
    private static String A = "A";
    /**
     * B锁
     */
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        }, "thread-001");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        }, "thread-002");
        t1.start();
        t2.start();
    }
}
/*
死锁了
给线程加上名字，便于调试
C:\Users\Alvin>jps
2024 Jps
5928 AppMain
7496
5772 Launcher
7100 RemoteMavenServer

C:\Users\Alvin>jstack 5928 > dump-5928.txt
>>>>该dump文件内容参见同目录下的dump-5928.txt
 */