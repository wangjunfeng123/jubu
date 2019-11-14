package com.ninep.jubu.test.singleTon;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 单例模式——懒汉模式.
 * @since 2018/11/4
 */
public class SingleTon1 {


    /**
     * 单例模式：
     * 懒汉模式
     * 懒汉：就是比较懒，啥时候用啥时候创建
     *
     * 线程安全：线程不安全
     */
    private SingleTon1 instance;
    private SingleTon1() {
    }

    public SingleTon1 getInstance() {
        if (instance == null) {
            return new SingleTon1();
        }
        return instance;
    }

}