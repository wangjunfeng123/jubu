package com.ninep.jubu.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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

        // 简单ioc容器XmlBeanFactory的创建
        // 1.根据xml配置文件创建resource对象，
        ClassPathResource resource = new ClassPathResource("application.xml");
        // 2. 创建DefaultListableBeanFactory 工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 3. 创建XmlBeanDefinitionReader读取器，载入beanDefinition。
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // 4.xmlBeanDefinitionReader执行载入beanDefinition的方法，最后完成bean的载入和注册，将bean放入IOC容器中。以后就可以从容器中获取bean
        reader.loadBeanDefinitions(resource);


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