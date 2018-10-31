package com.ninep.jubu.test.proxy;

import java.io.File;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/10/26
 */
public class MyClassLoader extends ClassLoader{

    private File classPathFile;

    //去class文件生成的位置读取class文件
    public MyClassLoader(){
        String path = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(path);
    }

    //加载class文件到jvm中
    public Class<?> findClass(String name) {

        return null;
    }

}