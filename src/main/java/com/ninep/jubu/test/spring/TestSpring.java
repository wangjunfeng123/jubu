package com.ninep.jubu.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc spring 源码分析.
 * @since 2018/11/25
 */
public class TestSpring implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    public static void main(String[] args) {

        FileSystemXmlApplicationContext fsac = new FileSystemXmlApplicationContext("test-spring.xml");

    }


    //设置applicationContext
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //获取applicationContext
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}