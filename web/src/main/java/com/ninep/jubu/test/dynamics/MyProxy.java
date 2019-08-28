package com.ninep.jubu.test.dynamics;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 手写动态代理.
 * @since 2018/10/26
 */
public class MyProxy {
    private static final String ENTER = "\r\n";

    public static Object newProxyInstance(MyClassLoader loader,Class<?>[] interfaces,MyInvocationHandler h) throws IllegalArgumentException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (h == null) {
            throw new NullPointerException();
        }

        //1、动态生成源代码
        String result = generateSrc(interfaces);

        //2、编译.java 文件，生成class文件
        String filePath = MyProxy.class.getResource("").getPath() + "$Proxy0.java";
        System.out.println(filePath);
        FileWriter fileWriter = new FileWriter(filePath);

        //读取生成的字符串
        fileWriter.write(result);
        fileWriter.flush();
        fileWriter.close();

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable iterable = fileManager.getJavaFileObjects(filePath);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, iterable);
        task.call();
        fileManager.close();

        //3、将class文件动态加载到jvm中
        Class<?> $ProxyO = loader.findClass("$Proxy0");
        Constructor<?> constructor = $ProxyO.getConstructor(MyInvocationHandler.class);

        //4、删除代理类
        File file = new File(filePath);
        file.delete();

        //5、返回对象
        return constructor.newInstance(h);
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package com.ninep.jubu.test.dynamics;" + ENTER + ENTER);
        stringBuilder.append("import com.ninep.jubu.test.dynamics.MyInvocationHandler;" + ENTER);
        stringBuilder.append("import java.lang.reflect.Method;" + ENTER);
        stringBuilder.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ENTER);
        stringBuilder.append("MyInvocationHandler h;" + ENTER);
        stringBuilder.append("public $Proxy0(MyInvocationHandler h) {" + ENTER);
        stringBuilder.append("this.h = h;" + ENTER);
        stringBuilder.append("return;" + ENTER);
        stringBuilder.append("}" + ENTER);

        for (Method method : interfaces[0].getMethods()) {
            stringBuilder.append("public " + method.getReturnType().getName() + " " + method.getName() + "() {" + ENTER);
            stringBuilder.append("try {" + ENTER);
            stringBuilder.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[]{});" + ENTER);
            stringBuilder.append("this.h.invoke(this, m, null);" + ENTER);
            stringBuilder.append("} catch(Throwable able) {" + ENTER);
            stringBuilder.append("able.getMessage();" + ENTER);
            stringBuilder.append("}" + ENTER);
            stringBuilder.append("}" + ENTER + ENTER);
        }
        stringBuilder.append("}" + ENTER);
        return stringBuilder.toString();
    }

}