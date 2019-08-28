package com.ninep.jubu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc The type Sale menu service.
 * @since 2019/8/4
 */
@Configuration
public class JubuWebConfig implements WebMvcConfigurer{

    public  void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.err.println("converters:" + converters);
    }
}