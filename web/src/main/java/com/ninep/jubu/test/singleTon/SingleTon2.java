package com.ninep.jubu.test.singleTon;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 单例模式——饿汉式
 * @since 2018/11/4
 */
public class SingleTon2 {

    /**
     * 单例模式：
     * 模式
     * 饿汉：都先创建了，啥时候用直接用
     *
     * 线程安全：线程不安全
     */
    private SingleTon2() {
    }

    private static SingleTon2 instance = new SingleTon2();

    public static SingleTon2 getInstance() {
        return instance;
    }

}