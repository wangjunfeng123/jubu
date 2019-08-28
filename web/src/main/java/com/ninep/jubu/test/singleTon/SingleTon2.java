package com.ninep.jubu.test.singleTon;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 单例模式——饿汉式
 * @since 2018/11/4
 */
public class SingleTon2 {

    private SingleTon2() {
    }

    private static SingleTon2 instance = new SingleTon2();

    public static SingleTon2 getInstance() {
        return instance;
    }

}