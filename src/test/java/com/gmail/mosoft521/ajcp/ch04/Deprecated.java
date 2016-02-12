package com.gmail.mosoft521.ajcp.ch04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 6-8
 */
public class Deprecated {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行暂停，输出内容工作停止
        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(20);
        // 将PrintThread进行恢复，输出内容继续
        printThread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行终止，输出内容停止
        printThread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }

    static class Runner implements Runnable {
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + " Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
/*
PrintThread Run at 17:30:40
PrintThread Run at 17:30:41
PrintThread Run at 17:30:42
main suspend PrintThread at 17:30:43
main resume PrintThread at 17:31:03
PrintThread Run at 17:31:03
PrintThread Run at 17:31:04
PrintThread Run at 17:31:05
PrintThread Run at 17:31:06PrintThread Run at 17:31:06main stop PrintThread at 17:31:06
 */