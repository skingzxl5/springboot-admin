package com.admin.mapper;

import com.admin.bean.BasiceMenuVo;
import com.admin.bean.BasicePermissionVo;
import com.admin.model.BasiceMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单列表 Mapper 接口
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
public interface BasiceMenuMapper extends BaseMapper<BasiceMenu> {

    /**
     * 根据角色ID查询角色菜单
     *
     * @param roleIds 角色id列表
     * @param type 类型
     * @return List<SysCustMenuVo>
     */
    List<BasiceMenuVo> findMenusByRoleIds(@Param("roleIds") Set<Integer> roleIds, @Param("type") Integer type);

    /**
     * 删除角色菜单关联
     *
     * @param roleId 角色ID
     * @param menuId 菜单id
     * @return int
     */
    int deleteBySys(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    /**
     * 根据用户查询角色菜单
     *
     * @param stId 用户id
     * @return List<SysCustMenuVo>
     */
    List<BasiceMenuVo> findMenusByUserId(Integer stId);

    /**
     * 根据用户查询权限按钮列表
     *
     * @param stId 用户id
     * @return List<SysCustMenuVo>
     */
    List<BasicePermissionVo> findPermissionByUserId(Integer stId);

    /**
     * 根据用户查询权限按钮集值
     *
     * @param stId 用户id
     * @return List<String>
     */
    List<String> findPermissionValueByUserId(Integer stId);

}
