package com.admin.service.impl;

import com.admin.model.BasiceRole;
import com.admin.mapper.BasiceRoleMapper;
import com.admin.service.BasiceRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Service
public class BasiceRoleServiceImpl extends ServiceImpl<BasiceRoleMapper, BasiceRole> implements BasiceRoleService {

    @Override
    public List<BasiceRole> findRolesByUserId(Integer stId){
        return baseMapper.findRolesByUserId(stId);
    }

}
