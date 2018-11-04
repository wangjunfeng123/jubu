package com.ninep.jubu.test.delegate;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2018/11/4
 */
public class WorkA implements Target {

    @Override
    public void doing() {
        System.out.println("doing AAAA");
    }
}