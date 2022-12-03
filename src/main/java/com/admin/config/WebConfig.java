package com.admin.config;

import com.admin.token.UserInfoResolver;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description WebConfig
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.servlet.file-win-path}")
    private String fileWinPath;
    @Value("${spring.servlet.file-linux-path}")
    private String fileLinuxPath;

    @SneakyThrows
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            path = fileWinPath;
        } else {
            path = fileLinuxPath;
        }
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + path);
    }

    @Resource
    private UserInfoResolver loginInfoResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginInfoResolver);
    }


    /**
     * 解决跨域问题 Cors
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 是否发送Cookie
                .allowCredentials(true)
                // 放行哪些原始域
                .allowedOriginPatterns("*")
                // 放行哪些请求方式
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                // 放行哪些原始请求头部信息
                .allowedHeaders("*")
                //表示预检（preflight）请求的有效期，在该有效期内，不用每次发送请求时都先发送预检请求，单位是秒。
                // 如果值设置为负值则表示使用默认值，默认值为1800秒，即30分钟。
                .maxAge(3600)
                // 暴露哪些头部信息
                .exposedHeaders("*");
    }
}