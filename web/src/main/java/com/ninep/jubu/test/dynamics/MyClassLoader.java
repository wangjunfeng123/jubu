package com.ninep.jubu.test.dynamics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

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
    public Class<?> findClass(String name) throws ClassNotFoundException{
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (null != classPathFile) {
            File classFile = new File(classPathFile, name + ".class");
            if (classFile.exists()) {
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    while((len = in.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                    }

                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != in) {
                            in.close();
                        }
                        if (null != out) {
                            out.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}