package com.ninep.jubu.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/12/29
 */
public class SayHelloImpl extends UnicastRemoteObject implements SayHello {


    protected SayHelloImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException{
        return "SayHelloImpl sayHello name:" + name;
    }

}