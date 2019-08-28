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
        // 一、简单ioc容器XmlBeanFactory的创建
        // 1.根据xml配置文件创建resource对象，
        ClassPathResource resource = new ClassPathResource("application.xml");
        // 2. 创建DefaultListableBeanFactory 工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 3. 创建XmlBeanDefinitionReader读取器，载入beanDefinition。
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // 4.xmlBeanDefinitionReader执行载入beanDefinition的方法，最后完成bean的载入和注册，将bean放入IOC容器中。以后就可以从容器中获取bean
        reader.loadBeanDefinitions(resource);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        // bean载入的过程：定位、载入、注册 三部曲

        // 高富帅IOC容器
        // FileSystemXmlApplicationContext("test-spring.xml")构造方法，最终指向了3个方法super(parent),setConfigLocations(configLocations);refresh();
        // super(parent):为容器设置好bean资源加载器，为ApplicationContext
        // this.setConfigLocations(configLocations);设置bean定义资源文件的路径,放入AbstractRefreshableConfigApplicationContext属性中
        // FileSystemXmlApplicationContext的父类AbstractApplicationContext中有静态代码块，
        // static{ContextClosedEvent.class.getName()} 防止在Weblogic容器关闭时出现类加载异常。
        // abstractApplicationContext 中的方法getResourcePatternResolver为了加载定位的资源   extend DefaultResourceLoader 也是个资源加载器

        // 核心方法：refresh()载入bean的过程
        // refresh方法在创建容器之前，如果有容器存在，则销毁后重新创建，相当于IOC容器的重启，容器初始化，bean的载入
        // refresh 方法在abstractApplicationContext 中，执行过程如下
        /*this.prepareRefresh(); // 准备刷新的方法，设置同步标示
        ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();// 获取工厂
        this.prepareBeanFactory(beanFactory);// beanFactory配置容器特征，例如：类加载器，事件处理器
        try {
            this.postProcessBeanFactory(beanFactory); // 指定特定的BeanPost指定特殊的时间处理器
            this.invokeBeanFactoryPostProcessors(beanFactory);
            this.registerBeanPostProcessors(beanFactory);
            this.initMessageSource();// 国际化
            this.initApplicationEventMulticaster(); // 初始化事件传播器
            this.onRefresh();// 调用子类bean的初始化方法
            this.registerListeners();// 为时间传播器注册时间监听器
            this.finishBeanFactoryInitialization(beanFactory); // 初始化所有剩余的单例bean
            this.finishRefresh(); //初始化容器的生命周期事件处理器，并发布容器的生命周期事件。
        } catch (BeansException var9) {
            // 销毁创建的bean
            this.destroyBeans();
            // 取消refresh查找，重启容器同步标识
            this.cancelRefresh(var9);*/

        // refresh 方法主要提供bean的生命周期管理
        // AbstractApplicationContext类中的obtainFreshBeanFactory 方法中，用来委派模式，让子类帮其实现具体的内容
        //





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