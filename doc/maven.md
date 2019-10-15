# maven文档



启动问题：

一、打包方式修改：
<packaging>jar<packaging/>：没有问题

<packaging>war<packaging/>
		问题：需要创建web.xml文件
				或者修改<maven



## 启动

- 1、java -jar web.jar

- 2、目录启动：
  	jar包启动：java org.springframework.boot.loader.JarLauncher
  	war包启动：java org.springframework.boot.loader.WarLauncher
  	

- 3、maven 启动
  	mvn spring-boot:run
  	

  官网：http://maven.apache.org/