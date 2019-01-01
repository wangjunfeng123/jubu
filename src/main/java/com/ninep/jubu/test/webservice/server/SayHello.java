package com.ninep.jubu.test.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 发布webservice方法.
 * @since 2018/12/31
 */
@WebService
public interface SayHello {

    @WebMethod
    String sayHello(String name);

}