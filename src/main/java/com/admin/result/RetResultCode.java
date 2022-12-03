package com.admin.result;

/**
 * @author skin
 * @Description: HTTP状态码枚举
 * @Date 2022-10-05
 */
public enum RetResultCode {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证（签名错误）"),
    NOT_FOUND(404, "接口不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    public int code;
    public String name;

    RetResultCode(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
