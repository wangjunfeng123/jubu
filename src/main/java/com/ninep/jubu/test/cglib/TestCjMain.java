package com.ninep.jubu.test.cglib;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 测试类
 * @since 2018/11/2
 */
public class TestCjMain {

    public static void main(String[] args) {
        Zhangsan o = (Zhangsan)new ZhangsanProxy().getInstance(new Zhangsan());
        o.findLove();
    }

}