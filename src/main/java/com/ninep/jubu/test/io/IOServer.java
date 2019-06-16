package com.ninep.jubu.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/6/16
 */
public class IOServer {

    // todo 这种情况适用于 连接数不是特别高 <1000
    /**
     * 模拟服务端
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();

                        new Thread(() -> {
                            try {
                                int len;
                                byte[] data = new byte[1024];
                                InputStream inputStream = socket.getInputStream();
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }

                            } catch (IOException e) {
                            }
                        }).start();

                    } catch (IOException e) {
                    }
                }

            }).start();
        } catch (IOException e) {
        }
    }

}