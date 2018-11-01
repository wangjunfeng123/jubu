package com.ninep.jubu.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/10/25
 */
public class MeipoProxy implements InvocationHandler {

    private Person person;

    //获取代理对象
    public Object getInstance(Person person) {
        this.person = person;
        Class clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行之前的东东");
        method.invoke(person,args);
        System.out.println("执行之后的东东");
        return null;
    }

}