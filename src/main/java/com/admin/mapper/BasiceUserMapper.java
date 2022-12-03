package com.admin.mapper;

import com.admin.bean.login.LoginInfo;
import com.admin.model.BasiceUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
public interface BasiceUserMapper extends BaseMapper<BasiceUser> {


    /**
     * 根据用户名查用户信息
     *
     * @param loginName 用户名
     * @return LoginInfo
     */
    LoginInfo loginInfoByLoginName(String loginName);

}
