package com.ninep.jubu.test.proxy;

import java.lang.reflect.Method;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/10/26
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}