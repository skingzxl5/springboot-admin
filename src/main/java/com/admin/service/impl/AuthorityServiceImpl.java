package com.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.admin.bean.login.LoginInfo;
import com.admin.bean.login.LoginNoResult;
import com.admin.bean.login.UserInfoParam;
import com.admin.model.BasiceVerify;
import com.admin.result.RetResult;
import com.admin.result.RetResultGenerate;
import com.admin.service.*;
import com.admin.token.UserInfo;
import com.admin.token.TokenUtil;
import com.admin.utils.AES256Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description AuthorityServiceImpl
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private static final Log log = LogFactory.getLog(AuthorityServiceImpl.class);

    @Value("${spring.servlet.file-win-path}")
    private String fileWinPath;
    @Value("${spring.servlet.file-linux-path}")
    private String fileLinuxPath;

    @Resource
    private BasiceUserService basiceUserService;

    @Resource
    private BasiceVerifyService basiceVerifyService;


    @Override
    public RetResult<LoginNoResult> login(UserInfoParam userInfoParam) {
        LoginNoResult loginNoResult = new LoginNoResult();
        String loginName = userInfoParam.getLoginName();
        String loginPassword = userInfoParam.getLoginPassword();
        LoginInfo loginInfo = basiceUserService.loginInfoByLoginName(loginName);
        log.info(String.format("根据用户名%s验证登录%s ", loginName, JSON.toJSONString(loginInfo)));
        if (loginInfo == null) {
            return RetResultGenerate.failResult("用户不存在或尚未分配任何权限");
        }
        String stw = loginInfo.getLoginPassword();
        if (!stw.equals(AES256Util.encode(loginPassword))) {
            return RetResultGenerate.failResult("用户名密码错误");
        }
        UserInfo currentUser = new UserInfo();
        BeanUtils.copyProperties(loginInfo, currentUser);
        String token = TokenUtil.getToken(currentUser);
        loginNoResult.setToken(token);
        loginNoResult.setLoginInfo(loginInfo);

        return RetResultGenerate.successResult(loginNoResult);
    }

    @Override
    public RetResult<LoginNoResult> webLogin(UserInfoParam userInfoParam) {
        LoginNoResult loginNoResult = new LoginNoResult();
        String loginName = userInfoParam.getLoginName();
        String loginPassword = userInfoParam.getLoginPassword();
        String machineCode = userInfoParam.getMachineCode();
        String verifyCode = userInfoParam.getVerifyCode();
        LoginInfo loginInfo = null;
        Integer verifyFlag = validate(machineCode, verifyCode);
        if (verifyFlag == 1) {
            return RetResultGenerate.failResult("请求中未携带设备ID");
        } else if (verifyFlag == 2) {
            return RetResultGenerate.failResult("请填写验证码");
        } else if (verifyFlag == 3) {
            return RetResultGenerate.failResult("验证码不存在或已过期");
        }
        if (loginInfo == null) {
            loginInfo = basiceUserService.loginInfoByLoginName(loginName);
            log.info(String.format("根据用户名%s验证登录%s ", loginName, JSON.toJSONString(loginInfo)));
        }
        if (loginInfo == null) {
            return RetResultGenerate.failResult("用户不存在或尚未分配任何权限");
        }
        String stw = loginInfo.getLoginPassword();
        if (!stw.equals(AES256Util.encode(loginPassword))) {
            return RetResultGenerate.failResult("用户名密码错误");
        }
        UserInfo currentUser = new UserInfo();
        BeanUtils.copyProperties(loginInfo, currentUser);
        String token = TokenUtil.getToken(currentUser);
        loginNoResult.setToken(token);
        loginNoResult.setLoginInfo(loginInfo);

        return RetResultGenerate.successResult(loginNoResult);
    }


    /**
     * 验证验证码
     */
    @Override
    public Integer validate(String deviceId, String verificationCode) {
        if (StringUtils.isBlank(deviceId)) {
            return 1;
        }
        //请填写验证码
        if (StringUtils.isBlank(verificationCode)) {
            return 2;
        }
        QueryWrapper<BasiceVerify> basiceVerifyQueryWrapper = new QueryWrapper<>();
//        basiceVerifyQueryWrapper.eq(BasiceVerify.DEVICEID, deviceId);
        basiceVerifyQueryWrapper.eq(BasiceVerify.VERIFICATION_CODE, verificationCode);
        basiceVerifyQueryWrapper.eq(BasiceVerify.DELETE_FLAG, 0);
        List<BasiceVerify> basiceVerifyList = basiceVerifyService.list(basiceVerifyQueryWrapper);
        if (basiceVerifyList.size() == 0) {
            return 3;
        }

        for (BasiceVerify verify : basiceVerifyList
        ) {
            verify.setDeleteFlag(1);
            verify.setUpdateTime(LocalDateTime.now());
            basiceVerifyService.updateById(verify);
            //验证码5分钟内过期
            LocalDateTime createTime = verify.getCreateTime();
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = java.time.Duration.between(createTime, currentTime);
            if (duration.toMinutes() > 5) {
                return 3;
            }
        }
        return 0;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws FileNotFoundException {
        String fileSavePath = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            fileSavePath = fileWinPath;
        } else {
            fileSavePath = fileLinuxPath;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        log.info(String.format("上传文件 %s", originalFilename));
        String fileName = String.valueOf(System.currentTimeMillis()) + "_" + originalFilename;
        String fileRemotePath = fileSavePath + fileName;
        File file = new File(fileRemotePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
            log.info("===========file upload success=======");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error(originalFilename + "文件上传FileNotFoundException异常" + e.getMessage());
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            log.error(originalFilename + "文件上传IOException异常" + e.getMessage());
            return "";
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件关闭错误", e);
            }
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/files/" + fileName;
        log.info(String.format("上传%s文件url:%s", originalFilename, url));
        return url;
    }


}
