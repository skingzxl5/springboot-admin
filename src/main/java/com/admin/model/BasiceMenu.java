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
 * 菜单列表
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("basice_menu")
@ApiModel(value="BasiceMenu对象", description="菜单列表")
public class BasiceMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上级菜单ID")
    private Integer parentId;

    @ApiModelProperty(value = "类型：0目录1菜单2按钮")
    private Integer type;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否外部链接(0否1是)")
    private Boolean outerFlag;

    @ApiModelProperty(value = "URL地址")
    private String url;

    @ApiModelProperty(value = "路由路径")
    private String path;

    @ApiModelProperty(value = "路由缓存：0否1是")
    private Boolean keepAlive;

    @ApiModelProperty(value = "页面文件路径")
    private String viewPath;

    @ApiModelProperty(value = "是否显示：0否1是")
    private Boolean showFlag;

    @ApiModelProperty(value = "权限值(按钮必填)")
    private String permission;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人ID")
    private Integer updateStId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String TYPE = "type";

    public static final String TITLE = "title";

    public static final String NAME = "name";

    public static final String ICON = "icon";

    public static final String OUTER_FLAG = "outer_flag";

    public static final String URL = "url";

    public static final String PATH = "path";

    public static final String KEEP_ALIVE = "keep_alive";

    public static final String VIEW_PATH = "view_path";

    public static final String SHOW_FLAG = "show_flag";

    public static final String PERMISSION = "permission";

    public static final String SORT = "sort";

    public static final String CREATE_ST_ID = "create_st_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_ST_ID = "update_st_id";

    public static final String UPDATE_TIME = "update_time";

}
