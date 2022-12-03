package com.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.admin.model.BasiceRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@ApiModel(value = "BasiceUserVo对象", description = "用户信息表")
public class BasiceUserVo {

    @ApiModelProperty(value = "用户ID，每个用户为唯一值")
    @TableId(value = "st_id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "机器码")
    private String machineCode;

    @ApiModelProperty(value = "创建人时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除标识")
    private Integer deleteFlag;

    @ApiModelProperty(value = "用户角色列表")
    private List<BasiceRole> roles;
}
