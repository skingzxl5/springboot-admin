package com.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("basice_role_user")
@ApiModel(value="BasiceRoleUser对象", description="用户权限表")
public class BasiceRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工ID")
    private Integer stId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人ID")
    private Integer updateStId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String ST_ID = "st_id";

    public static final String ROLE_ID = "role_id";

    public static final String CREATE_ST_ID = "create_st_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_ST_ID = "update_st_id";

    public static final String UPDATE_TIME = "update_time";

}
