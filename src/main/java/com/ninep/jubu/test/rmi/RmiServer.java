package com.ninep.jubu.test.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/12/29
 */
public class RmiServer {

    // rmi执行步骤
    // 1、 定义接口实现Remote
    // 2、 定义实现类，实现接口，并extends UnicastRemoteObject
    // 3、 下面就是使用：
    // 3.1 定义server端配置监听端口 固定用法
    // 3.2 定义client端配置，用client端调用server端，看是否可以调通


    public static void main(String[] args) {
        try {
            SayHello hello = new SayHelloImpl();

            LocateRegistry.createRegistry(8888);

            Naming.bind("rmi://localhost:8888/sayHello",hello);

            System.out.println("server start");

        } catch (RemoteException e) {

        } catch (MalformedURLException e) {

        } catch (AlreadyBoundException e) {

        }
    }

}