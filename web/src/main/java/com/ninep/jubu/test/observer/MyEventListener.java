package com.ninep.jubu.test.observer;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 监听器.
 * @since 2019/8/28
 */
public interface MyEventListener extends EventListener {
    void handleEvent(EventObject event);
}