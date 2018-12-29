package com.ninep.jubu.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc rmi.
 * @since 2018/12/29
 */
public interface SayHello extends Remote{

    public String sayHello(String name) throws RemoteException;

}