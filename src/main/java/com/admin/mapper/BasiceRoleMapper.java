package com.admin.mapper;

import com.admin.model.BasiceRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
public interface BasiceRoleMapper extends BaseMapper<BasiceRole> {

    /**
     * 根据用户id获取角色
     *
     * @param stId
     * @return
     */
    List<BasiceRole> findRolesByUserId(Integer stId);

}
