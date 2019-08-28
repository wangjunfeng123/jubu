package com.ninep.jubu.webservice;

import javax.jws.WebService;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc webservice 实现类.
 * @since 2018/12/31
 */
@WebService
public class SayHelloImpl implements SayHello {

    @Override
    public String sayHello(String name) {
        return "hello i am jack,you are" + name;
    }

}