package com.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.admin.bean.login.LoginInfo;
import com.admin.model.BasiceRoleUser;
import com.admin.model.BasiceUser;
import com.admin.mapper.BasiceUserMapper;
import com.admin.result.RetResult;
import com.admin.result.RetResultGenerate;
import com.admin.service.BasiceRoleUserService;
import com.admin.service.BasiceUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.utils.AES256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Service
public class BasiceUserServiceImpl extends ServiceImpl<BasiceUserMapper, BasiceUser> implements BasiceUserService {

    @Resource
    private BasiceRoleUserService basiceRoleUserService;



    @Override
    public LoginInfo loginInfoByLoginName(String loginName) {
        return baseMapper.loginInfoByLoginName(loginName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RetResult<String> userSaveOrUpdate(BasiceUser basiceUser) {

        if (basiceUser.getStId() == null) {
            //分配默认密码123456
            if (StringUtils.isBlank(basiceUser.getLoginPassword())) {
                basiceUser.setLoginPassword(AES256Util.encode("123456"));
            }
        }
        if (basiceUser.getCreateTime() == null) {
            basiceUser.setCreateTime(LocalDateTime.now());
        }
        basiceUser.setUpdateTime(LocalDateTime.now());
        boolean result = super.saveOrUpdate(basiceUser);
        Integer stId = basiceUser.getStId();
        //更新角色 删除旧的新增新的
        QueryWrapper<BasiceRoleUser> roleUserQueryWrapper = new QueryWrapper<>();
        roleUserQueryWrapper.eq(BasiceRoleUser.ST_ID, stId);
        basiceRoleUserService.remove(roleUserQueryWrapper);
        if (result && StringUtils.isNotEmpty(basiceUser.getRoleId())) {

            List<String> roleIds = Arrays.asList(basiceUser.getRoleId().split(","));
            if (!CollectionUtils.isEmpty(roleIds)) {
                for (String roleId : roleIds
                ) {
                    BasiceRoleUser newRoleUser = new BasiceRoleUser();
                    newRoleUser.setRoleId(Integer.valueOf(roleId));
                    newRoleUser.setStId(stId);
                    newRoleUser.setCreateTime(LocalDateTime.now());
                    basiceRoleUserService.save(newRoleUser);
                }
            }
        }
        return RetResultGenerate.successResult();
    }

}
