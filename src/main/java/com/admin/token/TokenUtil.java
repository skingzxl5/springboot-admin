package com.admin.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description TokenUtil
 **/
@Slf4j
public class TokenUtil {
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "adminManager";

    /**
     * 设置过期时间 毫秒
     */
    private static final long EXPIRE_DATE = 7 * 24 * 60 * 60 * 1000;

    public static <T> String getToken(T data) {

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            Map<String, Object> payloadMap = new HashMap<>();
            payloadMap.put("data", JSON.toJSONString(data));
            token = JWT.create()
                    .withHeader(header)
                    .withPayload(payloadMap)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("获取token异常", e);
            return null;
        }
        return token;
    }

    public static boolean verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("验证token异常", e);
            return false;
        }
    }

    public static UserInfo getTokenData(String token) {
        try {
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 验证签名
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> data = jwt.getClaims();
            for (Map.Entry<String, Claim> entry : data.entrySet()) {
                String key = entry.getKey();
                Claim claim = entry.getValue();
                String value = claim.asString();
                if ("data".equals(key)) {
                    UserInfo currentUser = JSONObject.parseObject(value, UserInfo.class);
                    return currentUser;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
//        LoginInfo loginInfo = new LoginInfo();
//        loginInfo.setStId(1);
//        loginInfo.setStName("姓名");
//        System.out.println(TokenUtil.getToken(loginInfo));
        UserInfo loginInfo = TokenUtil.getTokenData("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjoie1wic3RJZFwiOjEsXCJzdE5hbWVcIjpcIuWnk-WQjVwifSIsImV4cCI6MTY3MDM5OTg1N30.lsnYYzqRYFrj0Hri3PtDoXSyQycmMpVARLgIoy_O3OQ");
        System.out.println(JSONObject.toJSONString(loginInfo));
    }

}