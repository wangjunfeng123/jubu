package com.ninep.jubu.test.singleTon;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 写点注释吧.
 * @since 2019/11/14
 */
public class SingleTon5 {
    private static SingleTon5 singleTon5;

    private SingleTon5() {
    }

    /**
     * 懒汉
     * 线程安全
     * synchronized static 类的锁；
     * 没有static标示锁住的是对象，没有示例能调用的到
     */
    public static synchronized SingleTon5 getSingleTon5(){
        if (singleTon5 == null) {
            singleTon5 = new SingleTon5();
        }
        return singleTon5;
    }

}