package com.admin.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户列表查询参数
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Data
@ApiModel(value = "UserPageParam对象", description = "用户列表查询参数")
public class UserPageParam {

    @ApiModelProperty(value = "关键字")
    private String key;

    @ApiModelProperty(value = "当前页数")
    private Integer pageNo;
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;


}
