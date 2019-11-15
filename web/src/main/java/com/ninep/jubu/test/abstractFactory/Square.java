package com.ninep.jubu.test.abstractFactory;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 写点注释吧.
 * @since 2019/11/15
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square draw method");
    }

}