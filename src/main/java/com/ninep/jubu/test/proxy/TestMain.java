package com.ninep.jubu.test.proxy;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 动态代理测试 service.
 * @since 2018/10/25
 */
public class TestMain {

    public static void main(String[] args) {
        Person person = (Person)new MeipoProxy().getInstance(new Liming());
        person.findLove();
    }

}