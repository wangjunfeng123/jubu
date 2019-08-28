package com.ninep.jubu.test.observer;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/8/28
 */
public class EventSource {
    private final List<MyEventListener> listeners = new ArrayList<>();

    public void addListener(MyEventListener myEventListener) throws InterruptedException {
        listeners.add(myEventListener);
    }

    /**
     * 接收外部事件
     * @param eventObject event
     */
    public void notifyEvents(EventObject eventObject) {
        for (MyEventListener listener : listeners) {
            listener.handleEvent(eventObject);
        }
    }

}
