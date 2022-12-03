package com.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.admin.bean.BasiceMenuToRoleVo;
import com.admin.bean.BasiceMenuVo;
import com.admin.bean.BasicePermissionVo;
import com.admin.model.BasiceMenu;
import com.admin.mapper.BasiceMenuMapper;
import com.admin.model.BasiceMenuRole;
import com.admin.service.BasiceMenuRoleService;
import com.admin.service.BasiceMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.token.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单列表 服务实现类
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Service
public class BasiceMenuServiceImpl extends ServiceImpl<BasiceMenuMapper, BasiceMenu> implements BasiceMenuService {

    @Resource
    private BasiceMenuRoleService basiceMenuRoleService;

    @Override
    public List<BasiceMenuVo> findMenusByRoleIds(Set<Integer> roleIds, Integer type) {
        return baseMapper.findMenusByRoleIds(roleIds, type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setMenuToRole(BasiceMenuToRoleVo custMenuToRoleVo, UserInfo userInfo) {
        Integer roleId = custMenuToRoleVo.getRoleId();
        List<Integer> menuIds = custMenuToRoleVo.getMenuIds();
        baseMapper.deleteBySys(roleId, null);
        if (!CollectionUtils.isEmpty(menuIds)) {
            menuIds.forEach(menuId -> {
                BasiceMenuRole sysCustMenuRole = new BasiceMenuRole();
                sysCustMenuRole.setRoleId(roleId);
                sysCustMenuRole.setMenuId(menuId);
                if (userInfo != null && StringUtils.isEmpty(userInfo.getStId())) {
                    sysCustMenuRole.setCreateStId(Integer.valueOf(userInfo.getStId()));
                }
                sysCustMenuRole.setCreateTime(LocalDateTime.now());
                basiceMenuRoleService.save(sysCustMenuRole);
            });
        }
    }

    @Override
    public List<BasiceMenu> findAll() {
        return baseMapper.selectList(
                new QueryWrapper<BasiceMenu>().orderByAsc("parent_id,sort")
        );
    }

    @Override
    public List<BasiceMenuVo> findMenusByUserId(Integer stId) {
        return baseMapper.findMenusByUserId(stId);
    }

    @Override
    public List<BasicePermissionVo> findPermissionByUserId(Integer stId) {
        return baseMapper.findPermissionByUserId(stId);
    }

    @Override
    public List<String> findPermissionValueByUserId(Integer stId) {
        return baseMapper.findPermissionValueByUserId(stId);
    }


}
