package com.ninep.jubu.test.proxy;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 手写动态代理.
 * @since 2018/10/26
 */
public class MyProxy  {

    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h)
            throws IllegalArgumentException
    {
        if (h == null) {
            throw new NullPointerException();
        }
        return null;
    }

}