package com.ninep.helper;

import com.ninep.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.Date;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc rabbitMQ helper
 * @since 2019/9/3
 */
public class RabbitHelper {

    /**
     * 推送消息
     * 这是个生产者
     * 采用默认交换器：direct
     */
    public static void publisher() {
        // 获取连接
        Connection connection = RabbitMQUtils.getRabbitConnection();
        if (connection != null) {
            try {
                // 创建通道
                Channel channel = connection.createChannel();
                // 声明队列
                channel.queueDeclare("queueName", false, false, false, null);
                // 发送内容
                String content = String.format("现在时间：%s", new Date().getTime());
                System.out.println("发送的消息是：" + content);
                channel.basicPublish("","queueName",null,content.getBytes("UTF-8"));
                channel.close();// 关闭通道
                connection.close();// 关闭连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费消息
     * 这是个消费者
     * 采用默认交换器：direct
     */
    public static void consumer() {
        Connection connection = RabbitMQUtils.getRabbitConnection();
        if (connection != null) {
            try {
                Channel channel = connection.createChannel();
                channel.queueDeclare("queueName", false, false, false, null);

                // 创建订阅器，并单个处理消息
                GetResponse response = channel.basicGet("queueName", false);
                String content = new String(response.getBody(), "utf-8");
                channel.basicAck(response.getEnvelope().getDeliveryTag(), false);// 消息确认
                /**
                 消息的确认：
                 如果消息不确认，message的状态置位Unacked的（未确认）；下次不会再次给你发送消息，broker认为你没有准备好
                 消费者消费每条信息都必须要确认；

                 消息的拒绝：
                 断开连接：broker会把消息发送给其他的consumer
                 拒绝消息：channel.basicReject(long deliveryTag, boolean requeue)
                            requeue :true 从新放入队列分给其他的consumer
                            requeue：false 消息会放入“死信”队列
                  */


                System.out.println(content);

                /*// 创建订阅器，并接受所有消息
                channel.basicConsume("queueName", false, "", new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                               byte[] body) throws IOException {
                        String queueName = envelope.getRoutingKey();
                        String contentType = properties.getContentType();
                        String content = new String(body, "utf-8");
                        System.out.println("消费的内容是：" + content);
                        System.out.println("消费的对列名称是：" + queueName);
                        channel.basicAck(envelope.getDeliveryTag(),false);
                    }
                });*/
            } catch (IOException e) {
                System.out.println("读取消息错误");
            }
        }
    }

    public static void main(String[] args) {
        consumer();
    }

}