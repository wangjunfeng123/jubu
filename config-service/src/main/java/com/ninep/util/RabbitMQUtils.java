package com.ninep.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/9/3
 */
public class RabbitMQUtils {
    private static final Logger log = LoggerFactory.getLogger(RabbitMQUtils.class);

    /**
     * 获取rabbitMq 连接
     * @Link Connection
     */
    public static Connection getRabbitConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("dsjlm123");
        factory.setVirtualHost("/");
        factory.setHost("10.100.22.93");
        factory.setPort(5672);
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException e) {
            log.error("连接超时");
        } catch (IOException e) {
            log.error("io异常");
        }
        return connection;
    }


}