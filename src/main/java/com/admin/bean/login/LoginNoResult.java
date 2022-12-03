package com.admin.bean.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description LoginNoResult
 **/
@ApiModel(value = "登录返回值", description = "登录返回值")
@Data
public class LoginNoResult {
    @ApiModelProperty(value = "登录信息")
    private LoginInfo loginInfo;
    @ApiModelProperty(value = "token")
    private String token;
}
