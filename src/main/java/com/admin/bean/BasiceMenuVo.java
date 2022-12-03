package com.admin.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
@ApiModel(value = "BasiceMenuVo对象", description = "菜单列表对象")
public class BasiceMenuVo {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("上级菜单ID")
    private Integer parentId;

    @ApiModelProperty("类型：0目录1菜单2按钮")
    private Integer type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否外部链接(0否1是)")
    private Integer outerFlag;

    @ApiModelProperty("URL地址")
    private String url;

    @ApiModelProperty("路由路径")
    private String path;

    @ApiModelProperty("路由缓存：0否1是")
    private Integer keepAlive;

    @ApiModelProperty("页面文件路径")
    private String viewPath;

    @ApiModelProperty("是否显示：0否1是")
    private Integer showFlag;

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

    @ApiModelProperty("子菜单列表")
    private List<BasiceMenuVo> children;


}
