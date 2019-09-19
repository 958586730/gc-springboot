package com.yfc.gc.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.Arrays;

@Configuration
public class WebConfig {

    @Bean
    public HttpMessageConverter<Object> fastJsonHttpMessageConverter(){
        FastJsonHttpMessageConverter fast = new FastJsonHttpMessageConverter();
        fast.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        return fast;
    }

}
