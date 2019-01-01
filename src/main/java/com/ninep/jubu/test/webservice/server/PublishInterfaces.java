package com.ninep.jubu.test.webservice.server;

import javax.xml.ws.Endpoint;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 把接口发布出去.
 * @since 2018/12/31
 */
public class PublishInterfaces {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/jubu/hello", new SayHelloImpl());

        System.out.println("publish interfaces");
    }

}