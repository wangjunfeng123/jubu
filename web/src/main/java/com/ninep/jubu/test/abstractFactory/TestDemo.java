package com.ninep.jubu.test.abstractFactory;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 写点注释吧.
 * @since 2019/11/15
 */
public class TestDemo {

    public static void main(String[] args) {
        AbstractFactory shape = FactoryProducer.getFactory("Shape");

        Shape circle = shape.getShape("Circle");
        System.out.println(circle);
        circle.draw();
    }

}