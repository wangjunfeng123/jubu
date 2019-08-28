package com.ninep.jubu.test.dynamics;

import java.lang.reflect.Method;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 定义自己的动态代理接口 .
 * @since 2018/10/26
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}