package com.admin.service;

import com.admin.bean.BasiceMenuToRoleVo;
import com.admin.bean.BasiceMenuVo;
import com.admin.bean.BasicePermissionVo;
import com.admin.model.BasiceMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.admin.token.UserInfo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单列表 服务类
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
public interface BasiceMenuService extends IService<BasiceMenu> {
    /**
     * 根据角色ID查询角色菜单
     *
     * @param roleIds 角色id列表
     * @param type    类型
     * @return List<SysCustMenuVo>
     */
    List<BasiceMenuVo> findMenusByRoleIds(Set<Integer> roleIds, Integer type);

    /**
     * 角色分配菜单
     *
     * @param custMenuToRoleVo
     * @param userInfo
     * @return
     */
    void setMenuToRole(BasiceMenuToRoleVo custMenuToRoleVo, UserInfo userInfo);

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<BasiceMenu> findAll();

    /**
     * 根据用户查询角色菜单
     *
     * @param stId
     * @return List<BasiceMenuVo>
     */
    List<BasiceMenuVo> findMenusByUserId(Integer stId);

    /**
     * 根据用户查询权限按钮列表
     *
     * @param stId
     * @return List<BasicePermissionVo>
     */
    List<BasicePermissionVo> findPermissionByUserId(Integer stId);


    /**
     * 根据用户查询权限按钮集值
     *
     * @param stId
     * @return List<String>
     */
    List<String> findPermissionValueByUserId(Integer stId);

}
