package com.test.concurrency.threadPool;

import java.util.concurrent.*;

/**
 * Created by zhj on 2018/6/26.
 */

public class ThreadPoolTest {

    private static  final int CPU_SIZE = Runtime.getRuntime().availableProcessors();
    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

    static class MyThreadPoolExecutor extends ThreadPoolExecutor{

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(t.getName() + " will execute this task,");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("afterExecute");
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new MyThreadPoolExecutor(2*CPU_SIZE,4*CPU_SIZE,1, TimeUnit.DAYS,queue);

        ExecutorService ex = Executors.newFixedThreadPool(34);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        Thread t = new Thread();

        Future<Integer> future = pool.submit(t,122);
        try {
            System.out.println("future="+ future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("end");
        pool.shutdown();
    }
}