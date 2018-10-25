package com.ninep.jubu.test.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 动态代理测试 service.
 * @since 2018/10/25
 */
public class TestMain {

    public static void main(String[] args) {
        try {
            Person person = (Person)new MeipoProxy().getInstance(new Liming());
            person.findLove();

            //获取代理对象的字节码文件--->反编译成java文件
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            FileOutputStream fos = new FileOutputStream("$proxy.class");
            fos.write(bytes);
            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}