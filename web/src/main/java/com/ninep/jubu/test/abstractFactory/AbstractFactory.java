package com.ninep.jubu.test.abstractFactory;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 抽象工厂.
 * @since 2019/11/15
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);

}