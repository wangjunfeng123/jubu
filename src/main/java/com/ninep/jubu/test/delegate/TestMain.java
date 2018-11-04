package com.ninep.jubu.test.delegate;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public class TestMain {

    /**
     * 委派模式：
     * 委派模式和工厂模式很相似
     * 1、委派模式更注意执行的过程
     * 2、有委派类和被委派类
     * 3、委派类把任务交给被委派类执行
     * 4、工厂模式更注重返回的结果，不通的工厂返回的结果不通。
     */

    public static void main(String[] args) {
        Work work = new Work(new WorkB());
        work.doing();
    }

}