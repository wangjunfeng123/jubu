package com.ninep.jubu.test.dynamics;

import com.ninep.jubu.test.proxy.Person;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/10/25
 */
public class MyMeipoProxy implements MyInvocationHandler {

    private Person person;

    //获取代理对象
    public Object getInstance(Person person) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        this.person = person;
        Class clazz = person.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行之前的东东");
        Object invoke = method.invoke(person);
        System.out.println("执行之后的东东");
        return invoke;
    }

}