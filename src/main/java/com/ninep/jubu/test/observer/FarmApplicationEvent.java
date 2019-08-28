package com.ninep.jubu.test.observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/8/28
 */
public class FarmApplicationEvent extends ApplicationEvent {
    public FarmApplicationEvent(Object source) {
        super(source);
    }

    public static void main(String[] args) {
        // 注册监听器
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.addApplicationListener(applicationEvent ->
                System.out.println(applicationEvent.getSource()));

        context.refresh();

        // 发布监听事件
        context.publishEvent(new MyApplicationEvent("hello"));
        context.publishEvent(new MyApplicationEvent("1"));
        context.publishEvent(new MyApplicationEvent(new Integer(100)));

    }

    /**
     * 监听事件
     */
    private static class MyApplicationEvent extends ApplicationEvent {
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

}