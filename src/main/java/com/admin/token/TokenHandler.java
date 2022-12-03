package com.admin.token;

import com.alibaba.fastjson.JSONObject;
import com.admin.result.RetResult;
import com.admin.result.RetResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description token拦截 验证是否有效
 **/
@Component
@Slf4j
public class TokenHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null) {
            Boolean result = TokenUtil.verify(token);
            //判断绑定
            if (result) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        RetResult<String> ret = new RetResult<>();
        ret.setCode(RetResultCode.UNAUTHORIZED.code);
        ret.setCnMessage("token验证失败");
        PrintWriter out = null;
        out = response.getWriter();
        out.write(JSONObject.toJSONString(ret));
        out.flush();
        out.close();
        return false;
    }
}
