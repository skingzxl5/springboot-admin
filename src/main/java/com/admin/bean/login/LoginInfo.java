package com.admin.bean.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description 登录信息
 **/
@ApiModel(value = "登录信息", description = "登录信息")
@Data
public class LoginInfo {

    @ApiModelProperty(value = "用户ID，每个用户为唯一值")
    private Integer stId;

    @ApiModelProperty(value = "员工姓名")
    private String stName;

    @ApiModelProperty(value = "员工英文姓名")
    private String stEname;

    @ApiModelProperty(value = "手机号码")
    private String stPhone;

    @ApiModelProperty(value = "登录用户名")
    private String loginName;

    @ApiModelProperty(value = "登录用户密码")
    private String loginPassword;

    @ApiModelProperty(value = "头像URL")
    private String avatar;

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "机器码")
    private String machineCode;

    @ApiModelProperty(value = "创建人时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除标识")
    private Integer deleteFlag;



}
