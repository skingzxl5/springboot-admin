package com.admin.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 菜单列表
 * </p>
 *
 * @author skin
 * @since 2022-10-05
 */
@Getter
@Setter
@ApiModel(value = "BasicePermissionVo对象", description = "按钮列表对象")
public class BasicePermissionVo {

    private Integer id;

    @ApiModelProperty("上级菜单ID")
    private Integer parentId;

    @ApiModelProperty("类型：0目录1菜单2按钮")
    private Boolean type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("权限值(按钮必填)")
    private String permission;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("创建人ID")
    private Integer createStId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人ID")
    private Integer updateStId;

    @ApiModelProperty("修改时间")
    private Date updateTime;


}
