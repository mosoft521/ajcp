package com.gmail.mosoft521.ajcp.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 6-2
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws Exception {
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        Thread.currentThread().setPriority(8);
        System.out.println("done.");
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;

        for (Job job : jobs) {
            System.out.println("Job Priority : " + job.priority + ", Count : " + job.jobCount);
        }

    }

    static class Job implements Runnable {
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        public void run() {
            while (notStart) {
                Thread.yield();//让步
            }
            while (notEnd) {
                Thread.yield();//让步
                jobCount++;
            }
        }
    }
}
/*
done.
Job Priority : 1, Count : 9560257
Job Priority : 1, Count : 385876
Job Priority : 1, Count : 16023308
Job Priority : 1, Count : 23969111
Job Priority : 1, Count : 9229970
Job Priority : 10, Count : 23137608
Job Priority : 10, Count : 16672555
Job Priority : 10, Count : 24086779
Job Priority : 10, Count : 19041254
Job Priority : 10, Count : 23170081
 */