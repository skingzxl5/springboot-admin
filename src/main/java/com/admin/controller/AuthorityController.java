package com.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.admin.bean.*;
import com.admin.bean.login.LoginNoResult;
import com.admin.bean.login.UserInfoParam;
import com.admin.model.*;
import com.admin.result.RetResult;
import com.admin.result.RetResultGenerate;
import com.admin.service.*;
import com.admin.token.CurrentUserInfo;
import com.admin.token.UserInfo;
import com.admin.utils.AES256Util;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.captcha.utils.CaptchaUtil;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * <p>
 * 权限验证
 * </p>
 *
 * @author Skin
 * @since 2022-10-05
 */
@Api(value = "AuthorityController", tags = "权限验证接口")
@RestController
@RequestMapping("/api/authority")
public class AuthorityController {

    @Resource
    private AuthorityService authorityService;

    @Resource
    private BasiceVerifyService basiceVerifyService;

    @Resource
    private BasiceMenuService basiceMenuService;

    @Resource
    private BasiceUserService basiceUserService;

    @Resource
    private BasiceRoleService basiceRoleService;

    @Resource
    private BasiceRoleUserService basiceRoleUserService;

    @Resource
    private BasiceMenuRoleService basiceMenuRoleService;


    @ApiOperation("1.1登录")
    @PostMapping("/login")
    public RetResult<LoginNoResult> loginNo(@RequestBody UserInfoParam userInfoParam) {
        return authorityService.login(userInfoParam);
    }

    @ApiOperation("1.2web端登录")
    @PostMapping("/webLogin")
    public RetResult<LoginNoResult> webLogin(@RequestBody UserInfoParam userInfoParam) {
        return authorityService.webLogin(userInfoParam);
    }

    @ApiOperation(value = "1.3创建验证码")
    @GetMapping("/validata/code/{deviceId}")
    public void createCode(@PathVariable String deviceId, HttpServletResponse response) throws Exception {
        Assert.notNull(deviceId, "机器码不能为空");
        // 设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);
        // 三个参数分别为宽、高、位数
        GifCaptcha gifCaptcha = new GifCaptcha(100, 35, 4);
        // 设置类型：字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 保存验证码
        BasiceVerify basiceVerify = new BasiceVerify();
        basiceVerify.setDeviceId(deviceId);
        basiceVerify.setVerificationCode(gifCaptcha.text().toLowerCase());
        basiceVerify.setDeleteFlag(0);
        basiceVerify.setCreateTime(LocalDateTime.now());
        basiceVerifyService.save(basiceVerify);
        // 输出图片流
        gifCaptcha.out(response.getOutputStream());
    }

    @ApiOperation(value = "1.4查询当前用户菜单")
    @GetMapping("/menu/current")
    public RetResult<List<BasiceMenuVo>> findMyMenu(@ApiIgnore @CurrentUserInfo UserInfo userInfo) {
        List<BasiceMenuVo> menus = basiceMenuService.findMenusByUserId(userInfo.getStId());
        return RetResultGenerate.successResult(treeBuilder(menus));
    }

    @ApiOperation(value = "1.5查询当前用户按钮权限")
    @GetMapping("/menu/permission")
    public RetResult<List<BasicePermissionVo>> findMyPermission(@ApiIgnore @CurrentUserInfo UserInfo userInfo) {
        List<BasicePermissionVo> menus = basiceMenuService.findPermissionByUserId(userInfo.getStId());
        return RetResultGenerate.successResult(menus);
    }

    @ApiOperation(value = "1.6查询当前用户按钮权限集值")
    @GetMapping("/menu/permissionValue")
    public RetResult<List<String>> findMyPermissionValue(@ApiIgnore @CurrentUserInfo UserInfo userInfo) {
        List<String> menus = basiceMenuService.findPermissionValueByUserId(userInfo.getStId());
        return RetResultGenerate.successResult(menus);
    }


    @ApiOperation(value = "2.1新增或修改菜单")
    @PostMapping("/menu/saveOrUpdate")
    public RetResult<String> saveOrUpdate(@RequestBody BasiceMenu menu, @ApiIgnore @CurrentUserInfo UserInfo userInfo) {
        try {
            if (menu.getId() == null) {
                menu.setCreateStId(Integer.valueOf(userInfo.getStId()));
                menu.setCreateTime(LocalDateTime.now());
            } else {
                menu.setUpdateStId(Integer.valueOf(userInfo.getStId()));
                menu.setUpdateTime(LocalDateTime.now());
            }
            basiceMenuService.saveOrUpdate(menu);
            return RetResultGenerate.successResult("操作成功");
        } catch (Exception ex) {
            return RetResultGenerate.failResult("操作失败");
        }
    }

    @ApiOperation(value = "2.2删除菜单")
    @DeleteMapping("/menu/delete/{id}")
    public RetResult<String> delete(@PathVariable Integer id) {
        try {
            basiceMenuService.removeById(id);
            return RetResultGenerate.successResult("操作成功");
        } catch (Exception ex) {
            return RetResultGenerate.failResult("操作失败");
        }
    }

    @ApiOperation(value = "2.3根据roleId获取对应的菜单")
    @GetMapping("/menu/roleId/{roleId}")
    public RetResult<List<BasiceMenuVo>> findMenusByRoleId(@PathVariable Integer roleId) {
        Set<Integer> roleIds = new HashSet<>();
        roleIds.add(roleId);
        List<BasiceMenuVo> roleMenus = basiceMenuService.findMenusByRoleIds(roleIds, null);
        return RetResultGenerate.successResult(roleMenus);
    }

    @ApiOperation(value = "2.4角色分配菜单")
    @PostMapping("/menu/granted")
    public RetResult<String> setMenuToRole(@RequestBody BasiceMenuToRoleVo custMenuToRoleVo, @ApiIgnore @CurrentUserInfo UserInfo userInfo) {
        basiceMenuService.setMenuToRole(custMenuToRoleVo, userInfo);
        return RetResultGenerate.successResult("操作成功");
    }

    @ApiOperation(value = "2.5查询所有菜单")
    @GetMapping("/menu/findAll")
    public RetResult<List<BasiceMenu>> findAll() {
        List<BasiceMenu> list = basiceMenuService.findAll();
        return RetResultGenerate.successResult(list);
    }

    @ApiOperation(value = "3.1查询用户列表")
    @PostMapping("/users/page")
    public RetResult<IPage<BasiceUser>> userPage(@RequestBody UserPageParam userPageParam) {
        String key = userPageParam.getKey();
        QueryWrapper<BasiceUser> basiceUserQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            basiceUserQueryWrapper.like(BasiceUser.ST_NAME, key).or().like(BasiceUser.LOGIN_NAME, key);
        }
        Page<BasiceUser> page = new Page<>(userPageParam.getPageNo(), userPageParam.getPageSize());
        IPage<BasiceUser> basiceUserPage = basiceUserService.page(page, basiceUserQueryWrapper);
        List<BasiceUser> basiceUserList = basiceUserPage.getRecords();
        for (BasiceUser basiceUser : basiceUserList
        ) {
            List<BasiceRole> roleList = basiceRoleService.findRolesByUserId(basiceUser.getStId());
            basiceUser.setRoles(roleList);
        }
        return RetResultGenerate.successResult(basiceUserPage);
    }

    @ApiOperation(value = "3.2新增or更新用户")
    @PostMapping("/users/saveOrUpdate")
    public RetResult<String> userSaveOrUpdate(@RequestBody BasiceUser basiceUser) {
        return basiceUserService.userSaveOrUpdate(basiceUser);
    }

    @ApiOperation(value = "3.3修改用户")
    @PostMapping("/users/update")
    public RetResult<String> userUpdate(@RequestBody BasiceUser basiceUser) {
        BasiceUser currentBasiceUser = basiceUserService.getById(basiceUser.getStId());
        if (currentBasiceUser == null) {
            return RetResultGenerate.failResult("用户信息异常");
        }
        String loginPassword = basiceUser.getLoginPassword();
        if (!currentBasiceUser.getLoginPassword().equals(loginPassword)) {
            loginPassword = (AES256Util.encode(loginPassword));
        }
        currentBasiceUser.setAvatar(basiceUser.getAvatar());
        currentBasiceUser.setStName(basiceUser.getStName());
        currentBasiceUser.setLoginName(basiceUser.getLoginName());
        currentBasiceUser.setLoginPassword(loginPassword);
        currentBasiceUser.setStPhone(basiceUser.getStPhone());
        currentBasiceUser.setUpdateTime(LocalDateTime.now());
        basiceUserService.updateById(currentBasiceUser);

        return RetResultGenerate.successResult("操作成功");
    }

    @ApiOperation(value = "3.4删除用户")
    @DeleteMapping("/users/delete/{stId}")
    public RetResult<String> userDeleted(@PathVariable Integer stId) {
        BasiceUser basiceUser = basiceUserService.getById(stId);
        basiceUser.setDeleteFlag(1);
        basiceUser.setUpdateTime(LocalDateTime.now());
        basiceUserService.updateById(basiceUser);
        return RetResultGenerate.successResult();
    }

    @ApiOperation(value = "3.5恢复用户")
    @PostMapping("/users/restore/{stId}")
    public RetResult<String> userRestore(@PathVariable Integer stId) {
        BasiceUser basiceUser = basiceUserService.getById(stId);
        basiceUser.setDeleteFlag(0);
        basiceUser.setUpdateTime(LocalDateTime.now());
        basiceUserService.updateById(basiceUser);
        return RetResultGenerate.successResult();
    }

    @ApiOperation(value = "4.1查询角色列表")
    @PostMapping("/roles/page")
    public RetResult<IPage<BasiceRole>> rolePage(@RequestBody UserPageParam userPageParam) {
        String key = userPageParam.getKey();
        QueryWrapper<BasiceRole> basiceRoleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            basiceRoleQueryWrapper.like(BasiceRole.NAME, key);
        }
        Page<BasiceRole> page = new Page<>(userPageParam.getPageNo(), userPageParam.getPageSize());
        IPage<BasiceRole> basiceUserPage = basiceRoleService.page(page, basiceRoleQueryWrapper);
        return RetResultGenerate.successResult(basiceUserPage);
    }

    /**
     * @param sysRole
     * @return
     */
    @ApiOperation(value = "4.2角色新增或更新")
    @PostMapping("/roles/saveOrUpdate")
    public RetResult<String> saveOrUpdate(@RequestBody BasiceRole sysRole) {
        basiceRoleService.saveOrUpdate(sysRole);
        return RetResultGenerate.successResult("操作成功");
    }

    @ApiOperation(value = "4.3角色删除")
    @DeleteMapping("/roles/delete/{id}")
    public RetResult<String> deleteRole(@PathVariable Integer id) {
        try {
            if (id == 1) {
                return RetResultGenerate.failResult("管理员不可以删除");
            }
            basiceRoleService.removeById(id);
            QueryWrapper<BasiceRoleUser> roleUserQueryWrapper = new QueryWrapper<>();
            roleUserQueryWrapper.eq(BasiceRoleUser.ROLE_ID, id);
            basiceRoleUserService.remove(roleUserQueryWrapper);

            QueryWrapper<BasiceMenuRole> roleMenuQuery = new QueryWrapper<>();
            roleMenuQuery.eq(BasiceMenuRole.ROLE_ID, id);
            basiceMenuRoleService.remove(roleMenuQuery);

            return RetResultGenerate.successResult("操作成功");
        } catch (Exception e) {
            return RetResultGenerate.failResult("操作失败");
        }
    }

    @ApiOperation(value = "4.5查询全部角色")
    @GetMapping("/roles/all")
    public RetResult<List<BasiceRole>> rolesAll() {
        QueryWrapper<BasiceRole> roleQueryWrapper = new QueryWrapper<>();
        List<BasiceRole> roleList = basiceRoleService.list(roleQueryWrapper);
        return RetResultGenerate.successResult(roleList);
    }

    @ApiOperation("5.1文件上传")
    @PostMapping("/uploadFile")
    public RetResult<String> uploadFile(@ApiParam(value = "文件") @RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request) {
        try {
            return RetResultGenerate.successResult(authorityService.uploadFile(multipartFile, request));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return RetResultGenerate.successResult("文件上传异常");
    }


    /**
     * 两层循环实现建树
     *
     * @param sysCustMenus 菜单列表
     * @return List<BasiceMenuVo>
     */
    public static List<BasiceMenuVo> treeBuilder(List<BasiceMenuVo> sysCustMenus) {
        List<BasiceMenuVo> menus = new ArrayList<>();
        for (BasiceMenuVo sysMenu : sysCustMenus) {
            if (sysMenu.getParentId() == 0) {
                menus.add(sysMenu);
            }
            for (BasiceMenuVo menu : sysCustMenus) {
                if (menu.getParentId().equals(sysMenu.getId())) {
                    if (sysMenu.getChildren() == null) {
                        sysMenu.setChildren(new ArrayList<>());
                    }
                    sysMenu.getChildren().add(menu);
                }
            }
        }
        return menus;
    }

}
