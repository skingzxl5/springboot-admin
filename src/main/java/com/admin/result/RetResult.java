package com.admin.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description: return resultSet
 *
 */
@ApiModel(value = "RetResult对象", description = "返回值对象")
public class RetResult<T> {

    @ApiModelProperty(value = "返回信息")
    private String cnMessage;

    @ApiModelProperty(value = "返回信息")
    private String enMessage;

    @ApiModelProperty(value = "返回标识 1 成功， 0 失败")
    private String flag;

    @ApiModelProperty(value = "http状态码")
    private Integer code;

    @ApiModelProperty(value = "状态")
    private String messageData;

    @ApiModelProperty(value = "其他")
    private String remark;

    @ApiModelProperty(value = "返回集合")
    private T data;


    public String getCnMessage() {
        return cnMessage;
    }

    public void setCnMessage(String cnMessage) {
        this.cnMessage = cnMessage;
    }

    public String getEnMessage() {
        return enMessage;
    }

    public void setEnMessage(String enMessage) {
        this.enMessage = enMessage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }




}
