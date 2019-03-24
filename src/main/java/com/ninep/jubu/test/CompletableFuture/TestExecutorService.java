package com.ninep.jubu.test.CompletableFuture;

import java.util.concurrent.*;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc  test completable future service.
 * @since 2019/3/24
 */
public class TestExecutorService {

    public static void main(String[] args) {
    }

    /**
     * 测试 ExecutorService 多线程编程
     */
    public void testExecuteService() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });
        doSomethingElse();
        try {
            Double price = future.get(1,TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            //todo
        }
    }

    // 测试方法
    private void doSomethingElse() {
    }

    // 测试方法
    private Double doSomeLongComputation() {
        return 0.0;
    }

    // future 存在的问题
    // 1、等待所有的任务都完成了；


}