package com.ninep.jubu.test.abstractFactory;

/**
 * @author wangjunfeng.
 * @version 1.0
 * @desc 写点注释吧.
 * @since 2019/11/15
 */
public class FactoryProducer {

    /**
     * 获取factory
     * @param choice 选择工厂
     * @return abstractFactory
     */
    public static AbstractFactory getFactory(String choice) {
        if (choice.equals("Shape")) {
            return new ShapeFactory();
        } else if (choice.equals("Color")) {
            return new ColorFactory();
        }
        return null;
    }

}