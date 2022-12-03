package com.admin.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description 角色设置菜单对象
 **/
@ApiModel(value = "SysCustMenuToRoleVo对象", description = "角色设置菜单对象")
@Data
public class BasiceMenuToRoleVo {

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("菜单id列表")
    private List<Integer> menuIds;
}
