package com.admin.service;

import com.admin.bean.login.LoginInfo;
import com.admin.model.BasiceUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.admin.result.RetResult;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
public interface BasiceUserService extends IService<BasiceUser> {


    /**
     * 根据用户名查用户信息
     *
     * @param loginName 用户名
     * @return LoginInfo
     */
    LoginInfo loginInfoByLoginName(String loginName);
    /**
     * 新增or更新
     *
     * @param basiceUser
     * @return LoginInfo
     */
    RetResult<String> userSaveOrUpdate(BasiceUser basiceUser);

}
