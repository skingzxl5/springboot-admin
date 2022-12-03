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
 * 
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("basice_role")
@ApiModel(value="BasiceRole对象", description="")
public class BasiceRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色code")
    private String code;

    @ApiModelProperty(value = "角色名")
    private String name;

    private String nameEn;

    private String remark;

    @ApiModelProperty(value = "创建人ID")
    private Integer createStId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人ID")
    private Integer updateStId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String NAME_EN = "name_en";

    public static final String REMARK = "remark";

    public static final String CREATE_ST_ID = "create_st_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_ST_ID = "update_st_id";

    public static final String UPDATE_TIME = "update_time";

}
