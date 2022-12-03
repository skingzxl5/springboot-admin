package com.admin.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description 当前登录信息
 **/
@ApiModel(value = "当前登录信息", description = "当前登录信息")
@Data
public class UserInfo {

    @ApiModelProperty(value = "用户ID，每个用户为唯一值")
    private Integer stId;

    @ApiModelProperty(value = "员工姓名")
    private String stName;

    @ApiModelProperty(value = "手机号码")
    private String stPhone;

    @ApiModelProperty(value = "登录用户名")
    private String loginName;


}
