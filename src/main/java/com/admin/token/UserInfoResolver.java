package com.admin.token;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description 获取TOKEN信息写入注解@CurrentLoginInfo
 * 自定义的Resolver并没有生效，还需要添加到MVC WebConfig.java
 **/
@Component
public class UserInfoResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUserInfo.class) &&
                parameter.getParameterType().isAssignableFrom(UserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        // header中获取用户token
        String token = request.getHeader("Authorization");
        UserInfo currentUser = TokenUtil.getTokenData(token);
        return currentUser;
    }
}
