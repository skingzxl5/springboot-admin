package com.admin.bean.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description UserInfoParam
 **/
@ApiModel(value = "登录入参", description = "登录入参")
@Data
public class UserInfoParam {

    @ApiModelProperty(value = "用户代码")
    public String loginName;
    @ApiModelProperty(value = "用户密码")
    public String loginPassword;
    @ApiModelProperty(value = "机器码")
    public String machineCode;
    @ApiModelProperty(value = "验证码")
    public String verifyCode;
}
