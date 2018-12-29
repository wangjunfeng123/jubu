package com.ninep.jubu.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/12/29
 */
public class RmiClient {

    public static void main(String[] args) {
        try {
            SayHello sayHello = (SayHello) Naming.lookup("rmi://localhost:8888/sayHello");


            System.out.println(sayHello.sayHello("Make"));

        } catch (NotBoundException e) {

        } catch (MalformedURLException e) {

        } catch (RemoteException e) {

        }
    }

}