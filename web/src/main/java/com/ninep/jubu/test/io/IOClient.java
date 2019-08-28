package com.ninep.jubu.test.io;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc BIO test.
 * @since 2019/6/16
 */
public class IOClient {

    /**
     * 模拟多个请求访问bio的model
     */
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 3333);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }
                }

            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }
        }).start();
    }

}