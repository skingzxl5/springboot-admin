package com.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("basice_user")
@ApiModel(value = "BasiceUser对象", description = "用户信息表")
public class BasiceUser implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "头像URL")
    private String avatar;

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "机器码")
    private String machineCode;

    @ApiModelProperty(value = "创建人时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识")
    private Integer deleteFlag;

    @TableField(exist = false)
    private List<BasiceRole> roles;

    @TableField(exist = false)
    private String roleId;


    public static final String ST_ID = "st_id";

    public static final String ST_NAME = "st_name";

    public static final String ST_ENAME = "st_ename";

    public static final String ST_PHONE = "st_phone";

    public static final String LOGIN_NAME = "login_name";

    public static final String LOGIN_PASSWORD = "login_password";

    public static final String CREATE_ST_ID = "create_st_id";

    public static final String MACHINE_CODE = "machine_code";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DELETE_FLAG = "delete_flag";


}
