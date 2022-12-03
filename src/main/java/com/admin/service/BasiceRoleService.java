package com.admin.service;

import com.admin.model.BasiceRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
public interface BasiceRoleService extends IService<BasiceRole> {

    /**
     * 根据用户id获取角色
     *
     * @param stId
     * @return
     */
    List<BasiceRole> findRolesByUserId(Integer stId);

}
