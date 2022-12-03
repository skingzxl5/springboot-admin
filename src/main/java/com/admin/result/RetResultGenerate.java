package com.admin.result;

/**
 * @author skin
 * @Description: data generate RetResult
 * @Date 2022-10-05
 */
public class RetResultGenerate {

    public static <T> RetResult<T> successResult() {
        RetResult<T> ret = new RetResult<T>();
        ret.setFlag("1");
        ret.setCode(RetResultCode.SUCCESS.code);
        ret.setCnMessage(RetResultCode.SUCCESS.name);
        return ret;
    }

    public static <T> RetResult<T> successResult(T data) {
        RetResult<T> ret = new RetResult<T>();
        ret.setFlag("1");
        ret.setCode(RetResultCode.SUCCESS.code);
        ret.setCnMessage(RetResultCode.SUCCESS.name);
        ret.setData(data);
        return ret;
    }

    public static <T> RetResult<T> successResult(String messageData,T data) {
        RetResult<T> ret = new RetResult<T>();
        ret.setFlag("1");
        ret.setCode(RetResultCode.SUCCESS.code);
        ret.setMessageData(messageData);
        ret.setCnMessage(RetResultCode.SUCCESS.name);
        ret.setData(data);
        return ret;
    }

    public static <T> RetResult<T> successResult(String flag, String message, T data) {
        RetResult<T> ret = new RetResult<T>();
        ret.setFlag(flag);
        ret.setCode(RetResultCode.SUCCESS.code);
        ret.setCnMessage(message);
        ret.setData(data);
        return ret;
    }

    public static <T> RetResult<T> failResult(String message) {
        RetResult<T> ret = new RetResult<T>();
        ret.setCode(RetResultCode.FAIL.code);
        ret.setFlag("0");
        ret.setCnMessage(message);
        return ret;
    }

    public static <T> RetResult<T> failResult(String flag, String message) {
        RetResult<T> ret = new RetResult<T>();
        ret.setFlag(flag);
        ret.setCode(RetResultCode.FAIL.code);
        ret.setCnMessage(message);
        return ret;
    }

    public static <T> RetResult<T> failResult(int code, String message) {
        RetResult<T> ret = new RetResult<T>();
        ret.setFlag("0");
        ret.setCode(code);
        ret.setCnMessage(message);
        return ret;
    }

    public static <T> RetResult<T> dataResult(int code, String msg, T data) {
        RetResult<T> ret = new RetResult<T>();
        ret.setCode(code);
        ret.setCnMessage(msg);
        ret.setData(data);
        return ret;
    }

}
