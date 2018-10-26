package com.ninep.jubu.test.proxy;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/10/26
 */
public class TesetMyMain {

    public static void main(String[] args) {
        Person person = (Person)new MyMeipoProxy().getInstance(new Liming());
        person.findLove();
    }

}