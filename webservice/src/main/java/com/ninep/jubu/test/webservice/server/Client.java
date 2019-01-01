package com.ninep.jubu.test.webservice.server;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 测试webservice的调用
 * @since 2018/12/31
 */
public class Client {
    //执行步骤
    // 1、定义服务端接口
    // 2、发布服务端接口，webservice基于jdk，不需要其他的引入
    // 3、启动服务端，访问url+wsdl（webservice definition language）访问wsdl 的xml文件
    // 4、通过wsImport.exe 生成client端调用
    // 5、通过client端调用服务端代码，即可


    public static void main(String[] args) {
        SayHelloImplService service = new SayHelloImplService();
        SayHelloImpl hello = service.getSayHelloImplPort();
        System.out.println(hello.sayHello("zhangsan"));
    }

}