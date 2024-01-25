package com.example.puphub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") //允许跨域访问的路径
                .allowedOrigins("http://localhost:8080")    //允许跨域访问的源
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")  //允许请求的方法
                .maxAge(168000) //预检间隔时间
                .allowedHeaders("*")    //允许头部设置
                .allowCredentials(true);    //是否发送Cookie
    }
}
