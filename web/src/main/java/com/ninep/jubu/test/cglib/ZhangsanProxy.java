package com.ninep.jubu.test.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc cglib service.
 * @since 2018/11/2
 */
public class ZhangsanProxy implements MethodInterceptor{


    //获取对象是 zhangsan的子类
    public Object getInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        System.out.println("====================starting===============");
        proxy.invokeSuper(o, objects);
        System.out.println("=======================end==================");
        return null;
    }
}