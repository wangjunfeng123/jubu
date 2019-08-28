package com.ninep.jubu.test.singleTon;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 单例模式——懒汉模式.
 * @since 2018/11/4
 */
public class SingleTon1 {

    private static SingleTon1 instance;
    public static SingleTon1 getInstance() {
        if (instance == null) {
            return new SingleTon1();
        }
        return instance;
    }

    private SingleTon1() {
    }

}