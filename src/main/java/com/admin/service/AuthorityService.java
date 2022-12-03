package com.admin.service;

import com.admin.bean.login.LoginNoResult;
import com.admin.bean.login.UserInfoParam;
import com.admin.result.RetResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;


/**
 * @author skin
 * @Date 2022-10-05
 * @Description AuthorityService
 **/

public interface AuthorityService {

    /**
     * 登录
     *
     * @param userInfoParam
     * @return LoginNoResult
     */
    RetResult<LoginNoResult> login(UserInfoParam userInfoParam);
    /**
     * web端登录 有验证码
     *
     * @param userInfoParam
     * @return LoginNoResult
     */
    RetResult<LoginNoResult> webLogin(UserInfoParam userInfoParam);

    /**
     * 验证验证码
     *
     * @param deviceId
     * @param VerificationCode
     * @return Integer
     */
    Integer validate(String deviceId,String VerificationCode);

    /**
     * 文件上传
     *
     * @param multipartFile
     * @param request
     * @return RetResult<String>
     */
    String uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws FileNotFoundException;




}
