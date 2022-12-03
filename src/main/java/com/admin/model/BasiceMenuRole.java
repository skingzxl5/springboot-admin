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
 * 角色与菜单权限关联表
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("basice_menu_role")
@ApiModel(value="BasiceMenuRole对象", description="角色与菜单权限关联表")
public class BasiceMenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人ID")
    private Integer updateStId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String MENU_ID = "menu_id";

    public static final String CREATE_ST_ID = "create_st_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_ST_ID = "update_st_id";

    public static final String UPDATE_TIME = "update_time";

}
