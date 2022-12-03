package com.admin.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description InterceptorConfig
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TokenHandler tokenHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(tokenHandler);
        //拦截配置
        registration.addPathPatterns("/api/admin/**", "/api/authority/**");
        //排除配置
        registration.excludePathPatterns("/api/authority/login","/api/authority/webLogin", "/api/authority/uploadFile", "/api/authority/validata/**");
    }
}