package com.ninep.jubu.test.proxy;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 手写动态代理.
 * @since 2018/10/26
 */
public class MyProxy {

    private static final String ENTER = "\r\n";


    public static Object newProxyInstance(MyClassLoader loader,Class<?>[] interfaces,MyInvocationHandler h) throws IllegalArgumentException {
        if (h == null) {
            throw new NullPointerException();
        }

        //1、动态生成源代码

        //2、编译.java 文件，生成class文件

        //3、将class文件动态加载到jvm中

        //4、删除代理类

        //5

        return null;
    }

}