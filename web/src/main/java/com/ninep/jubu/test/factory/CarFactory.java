package com.ninep.jubu.test.factory;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/3
 */
public class CarFactory {

    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        String name = "Benz";
        System.out.println(carFactory.build(name).getClass());
    }

    private Car build(String name) {
        if ("Benz".equalsIgnoreCase(name)) {
            return new Benz();
        } else if ("Audi".equalsIgnoreCase(name)) {
            return new Audi();
        } else {
            return null;
        }
    }
}