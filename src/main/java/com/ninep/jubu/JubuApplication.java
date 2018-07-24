package com.ninep.jubu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.ninep.jubu.mapper")
@ComponentScan("com.ninep.jubu")
@ImportResource("classpath:application-mybatis.xml")
@EnableScheduling//定时器
@SpringBootApplication
public class JubuApplication {

	public static void main(String[] args) {
		SpringApplication.run(JubuApplication.class, args);
	}
}
