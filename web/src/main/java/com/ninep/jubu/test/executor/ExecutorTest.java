package com.ninep.jubu.test.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 线程池.
 * @since 2019/9/14
 */
public class ExecutorTest {
    private static ExecutorService executor = Executors.newFixedThreadPool(12);

    private static void testMethod() {
        executor.execute(new Task());
    }


    /**
     * main 方法
     * @param args 参数
     */
    public static void main(String[] args) {
        testMethod();
    }

    /**
     * 要执行的任务
     */
    public static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(111);
        }
    }

}