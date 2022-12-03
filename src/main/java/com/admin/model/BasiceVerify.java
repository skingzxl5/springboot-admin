package com.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录验证码
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("basice_verify")
@ApiModel(value="BasiceVerify对象", description="登录验证码")
public class BasiceVerify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "设备ID")
    @TableField("deviceId")
    private String deviceId;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Integer createStId;

    @ApiModelProperty(value = "删除标识")
    private Integer deleteFlag;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String DEVICEID = "deviceId";

    public static final String VERIFICATION_CODE = "verification_code";

    public static final String REMARK = "remark";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_ST_ID = "create_st_id";

    public static final String DELETE_FLAG = "delete_flag";

    public static final String UPDATE_TIME = "update_time";

}
