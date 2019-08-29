# spring boot 启动过程

- 主要分为2个过程,构建`SpingApplication`和 SpringApplication.run()调用run方法

## 构建springApplication

```java
@SpringBootApplication
public class FarmApplication {
	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
	}
}
```

调用 SpringApplication #run

## SpringApplication 构造

```
SpringApplication构造方法如下：
1、初始化MainApplication
2、设置ApplicationListener和ApplicationEvent
3、设置ApplicationContextInitializer
```



springApplication源码：

```java
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		this.resourceLoader = resourceLoader;
		Assert.notNull(primarySources, "PrimarySources must not be null");
        //把sources设置到SpringApplication的sources属性中，目前只是一个MyApplication类对象
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
       // 判断应用的类型，servlet程序，reactive，None
		this.webApplicationType = deduceWebApplicationType();
       // ApplicationContextInitializer 做一些初始化工作
		setInitializers(
          (Collection)getSpringFactoriesInstances(ApplicationContextInitializer.class));
       // ApplicationListener 做事件监听初始化
		setListeners((Collection)getSpringFactoriesInstances(ApplicationListener.class));
       // 栈中找出main类，FarmApplication.class
		this.mainApplicationClass = deduceMainApplicationClass();
}
```



## SpringApplication.run()执行

`SpringApplicationRunListeners` 是监听SpringApplication启动的监听器.

`EventPublishingRunListener`  implements `SpringApplicationRunListener`



```
定义了7个method：：
starting：ApplicationStartingEvent事件对象
environmentPrepared：ApplicationEnvironmentPreparedEvent
contextPrepared：null
contextLoaded：ApplicationPreparedEvent
started：ApplicationStartedEvent是它的事件对象
running：ApplicationReadyEvent
failed：ApplicationFailedEvent事件对象

这些事件都继承自 SpringApplicationEvent
```

![startup2](C:\Users\Administrator\Desktop\startup2.jpg)



源码分析：

```java
public ConfigurableApplicationContext run(String... args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		configureHeadlessProperty();
		SpringApplicationRunListeners listeners = getRunListeners(args);
       // 这里接受ApplicationStartingEvent事件的listener会执行相应的操作
		listeners.starting();
		try {
           ApplicationArguments applicationArguments = 
                        new DefaultApplicationArguments(args);
            // 广播出ApplicationEnvironmentPreparedEvent事件给相应的监听器执行
            ConfigurableEnvironment environment = 						                                                   prepareEnvironment(listeners,applicationArguments);
            //创建应用程序的环境信息。如果是web程序，创建StandardServletEnvironment；否则，创建StandardEnvironment
			configureIgnoreBeanInfo(environment);
            // 打印banner
			Banner printedBanner = printBanner(environment);
             // 创建Spring容器
			context = createApplicationContext();
			exceptionReporters = getSpringFactoriesInstances(
					SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
            // 准备上下文
			prepareContext(context, environment, listeners, 
                           applicationArguments,printedBanner);
            // 刷新上下文
			refreshContext(context);
            // 容器创建完成之后执行额外一些操作
			afterRefresh(context, applicationArguments);
             // 执行结束，记录执行时间
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass)
						.logStarted(getApplicationLog(), stopWatch);
			}
            // 这里接受ApplicationStartedEvent事件的listener会执行相应的操作
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, listeners);
			throw new IllegalStateException(ex);
		}
		try {listeners.running(context);}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, null);
			throw new IllegalStateException(ex);
		}
		return context;
	}
```

prepareContext做上下文之前的准备：

```java
private void prepareContext(ConfigurableApplicationContext context,
			ConfigurableEnvironment environment, SpringApplicationRunListeners listeners,
			ApplicationArguments applicationArguments, Banner printedBanner) {
       // 配置环境
		context.setEnvironment(environment);
       // 
		postProcessApplicationContext(context);
		applyInitializers(context);
       // ApplicationContextPreparedEvent事件
		listeners.contextPrepared(context);
		if (this.logStartupInfo) {
			logStartupInfo(context.getParent() == null);
			logStartupProfileInfo(context);
		}
		// 把应用程序参数持有类注册到Spring容器中，并且是一个单例
		context.getBeanFactory().registerSingleton("springApplicationArguments",
				applicationArguments);
		if (printedBanner != null) {
			context.getBeanFactory().registerSingleton("springBootBanner", printedBanner);
		}
		// Load the sources
		Set<Object> sources = getAllSources();
		Assert.notEmpty(sources, "Sources must not be empty");
		load(context, sources.toArray(new Object[0]));
		listeners.contextLoaded(context);
	}
```



```java
protected void postProcessApplicationContext(ConfigurableApplicationContext context) {
    // 如果SpringApplication设置了是实例命名生成器，注册到Spring容器中
		if (this.beanNameGenerator != null) {
			context.getBeanFactory().registerSingleton(
					AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR,
					this.beanNameGenerator);
		}
    // 如果SpringApplication设置了资源加载器，设置到Spring容器中
		if (this.resourceLoader != null) {
			if (context instanceof GenericApplicationContext) {
				((GenericApplicationContext) context)
						.setResourceLoader(this.resourceLoader);
			}
			if (context instanceof DefaultResourceLoader) {
				((DefaultResourceLoader) context)
						.setClassLoader(this.resourceLoader.getClassLoader());
			}
		}
	}
```

这样run方法执行完成之后。Spring容器也已经初始化完成，各种监听器和初始化器也做了相应的工作。





在run方法调用之前，也就是构造SpringApplication的时候会进行初始化的工作，初始化的时候会做以下几件事：

1. 把参数sources设置到SpringApplication属性中，这个sources可以是任何类型的参数。本文的例子中这个sources就是MyApplication的class对象
2. 判断是否是web程序，并设置到webEnvironment这个boolean属性中
3. 找出所有的初始化器，默认有5个，设置到initializers属性中
4. 找出所有的应用程序监听器，默认有9个，设置到listeners属性中
5. 找出运行的主类(main class)

SpringApplication构造完成之后调用run方法，启动SpringApplication，run方法执行的时候会做以下几件事：

1. 构造一个StopWatch，观察SpringApplication的执行
2. 找出所有的SpringApplicationRunListener并封装到SpringApplicationRunListeners中，用于监听run方法的执行。监听的过程中会封装成事件并广播出去让初始化过程中产生的应用程序监听器进行监听
3. 构造Spring容器(ApplicationContext)，并返回
   3.1 创建Spring容器的判断是否是web环境，是的话构造AnnotationConfigEmbeddedWebApplicationContext，否则构造AnnotationConfigApplicationContext
   3.2 初始化过程中产生的初始化器在这个时候开始工作
   3.3 Spring容器的刷新(完成bean的解析、各种processor接口的执行、条件注解的解析等等)
4. 从Spring容器中找出ApplicationRunner和CommandLineRunner接口的实现类并排序后依次执行





































