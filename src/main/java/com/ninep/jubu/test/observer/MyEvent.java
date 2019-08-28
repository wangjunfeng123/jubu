package com.ninep.jubu.test.observer;

import java.util.EventObject;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc java的事件监听机制
 * EventObject and EventListener
 * @since 2019/8/28
 */
public class MyEvent extends EventObject {
    // 这是一个事件，注册好事件源
    // 当事件源触发时，执行监听内容
    public MyEvent(EventSource source) {
        super(source);
    }

    public void doEvent() {
        System.out.println("do event message");
    }

    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        try {
            System.out.println("============");
            eventSource.addListener(event -> {
                if (event.getSource().equals("sir")) {
                    System.out.println("Hello world");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventSource.notifyEvents(new EventObject("sir body"));
    }

}
