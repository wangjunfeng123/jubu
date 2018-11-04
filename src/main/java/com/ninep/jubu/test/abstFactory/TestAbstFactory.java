package com.ninep.jubu.test.abstFactory;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public class TestAbstFactory {

    //Spring 中工程代理
    //BeanFactory（生产bean）

    public static void main(String[] args) {
        DefaultFactory factory = new DefaultFactory();
        System.out.println(factory.getCar());

        System.out.println(factory.getCar("Benz"));
    }
}