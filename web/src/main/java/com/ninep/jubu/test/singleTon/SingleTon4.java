package com.ninep.jubu.test.singleTon;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 单例模式——静态内部类的单例.
 * @since 2018/11/4
 */
public class SingleTon4 {
    private SingleTon4() {}

    private static class ProxyInstance{
        private static final SingleTon4 instance = new SingleTon4();
    }

    public static SingleTon4 getInstance() {
        return ProxyInstance.instance;
    }
}