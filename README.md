# admin-service

## 介绍
这是一个简单的基于springboot构建的权限管理API接口服务，权限配套于vue的web项目。

主要涉及用户登录权限、用户角色分配、角色可访问前端web页面权限、角色页面按钮权限

## 软件架构

基于*角色*访问控制(Role-Based Access Control)模型

RBAC（Role-Based Access Control，基于角色的访问控制），就是用户通过角色与权限进行关联。简单地说，一个用户拥有若干角色，每一个角色拥有若干权限。这样，就构造成“用户-角色-权限”的授权模型。在这种模型中，用户与角色之间，角色与权限之间，一般者是多对多的关系。



- 用户：是发起操作的主体，可以是1个人，也可以是1个用户群组。例如：系统的登录用户等。


- 角色：用于连接了用户和权限的桥梁，每个角色可以关联多个权限，同时一个用户也可以关联多个角色，那么这个用户就有了多个角色的多个权限。
- 权限：用户可以访问的资源权限，包括：页面权限、操作按钮权限、数据访问权限。


## 数据库模型

1.  ### 用户资料表

    ```sql
    CREATE TABLE `basice_user` (
      `st_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID，每个用户为唯一值',
      `st_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
      `st_ename` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '员工英文姓名',
      `st_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
      `login_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录用户名',
      `login_password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录用户密码',
      `create_st_id` int DEFAULT NULL COMMENT '创建人ID',
      `machine_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '机器码',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `delete_flag` int(1) unsigned zerofill NOT NULL COMMENT '删除标识',
      `update_time` datetime DEFAULT NULL COMMENT '修改时间',
      `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像URL',
      PRIMARY KEY (`st_id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
    ```

    ​

2.  ### 角色表

    ```sql
    CREATE TABLE `basice_role` (
      `id` int NOT NULL AUTO_INCREMENT,
      `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色code',
      `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
      `name_en` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
      `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
      `create_st_id` int DEFAULT NULL COMMENT '创建人ID',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_st_id` int DEFAULT NULL COMMENT '修改人ID',
      `update_time` datetime DEFAULT NULL COMMENT '修改时间',
      PRIMARY KEY (`id`) USING BTREE,
      KEY `idx_code` (`code`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
    ```

    ​

3.  ### 权限表 -- 基于vue前端框架设计

    ```sql
    CREATE TABLE `basice_menu` (
      `id` int NOT NULL AUTO_INCREMENT,
      `parent_id` int NOT NULL COMMENT '上级菜单ID',
      `type` tinyint(1) NOT NULL COMMENT '类型：0目录1菜单2按钮',
      `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
      `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
      `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
      `outer_flag` tinyint(1) DEFAULT '0' COMMENT '是否外部链接(0否1是)',
      `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'URL地址',
      `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路由路径',
      `keep_alive` tinyint(1) DEFAULT NULL COMMENT '路由缓存：0否1是',
      `view_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '页面文件路径',
      `show_flag` tinyint(1) DEFAULT NULL COMMENT '是否显示：0否1是',
      `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限值(按钮必填)',
      `sort` int DEFAULT NULL COMMENT '排序',
      `create_st_id` int DEFAULT NULL COMMENT '创建人ID',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_st_id` int DEFAULT NULL COMMENT '修改人ID',
      `update_time` datetime DEFAULT NULL COMMENT '修改时间',
      PRIMARY KEY (`id`) USING BTREE,
      KEY `idx_sys_vue_menu_id` (`parent_id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='菜单列表';
    ```

    ​

4.  ### 用户关联角色表

    ```sql
    CREATE TABLE `basice_role_user` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `st_id` bigint NOT NULL COMMENT '员工ID',
      `role_id` bigint NOT NULL COMMENT '角色ID',
      `create_st_id` int DEFAULT NULL COMMENT '创建人ID',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_st_id` int DEFAULT NULL COMMENT '修改人ID',
      `update_time` datetime DEFAULT NULL COMMENT '修改时间',
      PRIMARY KEY (`id`) USING BTREE,
      UNIQUE KEY `index_act_staffpermission_1` (`st_id`,`role_id`) USING BTREE,
      KEY `index_aact_staffpermission_st_id` (`st_id`) USING BTREE,
      KEY `index_act_staffpermission_rl_id` (`role_id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=10633 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户权限表';
    ```

    ​

5.  角色关联权限表

    ```sql
    CREATE TABLE `basice_menu_role` (
      `id` int NOT NULL AUTO_INCREMENT,
      `role_id` int NOT NULL COMMENT '角色id',
      `menu_id` int NOT NULL COMMENT '菜单id',
      `create_st_id` int DEFAULT NULL COMMENT '创建人ID',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_st_id` int DEFAULT NULL COMMENT '修改人ID',
      `update_time` datetime DEFAULT NULL COMMENT '修改时间',
      PRIMARY KEY (`id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=1551 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色与菜单权限关联表';
    ```

    ​

## 功能点介绍

### 登录

/api/authority/webLogin

- 先查询用户是否存在，存在则获取到密码
- 数据库中密码是经过AES加密的，需要输入密码加密后进行效验
- AES加密/解密 通用方法类 AES256Util.java

### 验证码效验

正常设计逻辑：生成验证码图片，保存验证码文本到redis数据库中，设置key的有效期（例如5分钟），登录时带上验证码，查询redis数据库中是否存在。

本项目不同：把验证码文本放入mysql数据库中

- 生成验证码前端web展示，必须是图片形式，这里使用了easy-captcha

  ```xml
          <!-- 验证码生成工具 -->
          <dependency>
              <groupId>com.github.whvcse</groupId>
              <artifactId>easy-captcha</artifactId>
              <version>1.6.2</version>
          </dependency>
  ```

  ​

- 查询数据库中验证码是否存在，并且判断当前时间 减 验证码创建时间间隔 小于 5分钟

- 删除数据库中验证码记录

### token效验

本案例使用JWT验证token

```xml
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.19.2</version>
        </dependency>
```

```java
//token相关代码包
package com.admin.token;
```

- 用户登录成功后，用户信息生成token字符串并接口返回。
- 前端请求头加上Authorization 参数，填写token字符串
- 设置验证token的接口，不验证token的接口
- 解析token，并全局设置用户信息供注解使用

### 全局用户信息注解

添加注解

```java
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUserInfo {
}
```

给注解赋值

```java
@Component
public class UserInfoResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUserInfo.class) &&
                parameter.getParameterType().isAssignableFrom(UserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        // header中获取用户token
        String token = request.getHeader("Authorization");
        UserInfo currentUser = TokenUtil.getTokenData(token);
        return currentUser;
    }
}

```

```java
@Data
public class UserInfo {

    @ApiModelProperty(value = "用户ID，每个用户为唯一值")
    private Integer stId;

    @ApiModelProperty(value = "员工姓名")
    private String stName;

    @ApiModelProperty(value = "手机号码")
    private String stPhone;

    @ApiModelProperty(value = "登录用户名")
    private String loginName;

}
```

具体方法使用

```java
    public RetResult<List<String>> findMyPermissionValue(@ApiIgnore @CurrentUserInfo UserInfo userInfo) {
        List<String> menus = basiceMenuService.findPermissionValueByUserId(userInfo.getStId());
        return RetResultGenerate.successResult(menus);
    }
```



### 权限控制 - 可展示页面

- 根据用户查询页面权限列表数据

  ```java
      @ApiOperation(value = "1.4查询当前用户菜单")
      @GetMapping("/menu/current")
      public RetResult<List<BasiceMenuVo>> findMyMenu(@ApiIgnore @CurrentUserInfo UserInfo userInfo) {
          List<BasiceMenuVo> menus = basiceMenuService.findMenusByUserId(userInfo.getStId());
          return RetResultGenerate.successResult(treeBuilder(menus));
      }
  ```

- 前端根据数据生成路由，并且展示到菜单栏

### 权限控制 - 可操作按钮

- 查询用户的权限值集合

  ```java
      @ApiOperation(value = "1.6查询当前用户按钮权限集值")
      @GetMapping("/menu/permissionValue")
      public RetResult<List<String>> findMyPermissionValue(@ApiIgnore @CurrentUserInfo UserInfo userInfo) {
          List<String> menus = basiceMenuService.findPermissionValueByUserId(userInfo.getStId());
          return RetResultGenerate.successResult(menus);
      }
  ```

- 前端开发控制按钮是否可用

### 用户管理

新增

删除

修改

分配角色

### 角色管理

新增

删除

修改

分配权限

### 权限管理

新增

删除

修改

## 代码结构

```
└─main
    ├─java
    │  └─com
    │      └─admin
    │          ├─bean                      # 实体类
    │          │  └─login                  # 登录的实体类
    │          ├─config                    # 配置文件 MybatisPlusConfig分页配置 SwaggerConfig配置 WebConfig配置图片URL、跨域CORS 
    │          ├─controller                # 控制
    │          ├─generator                 # java代码生成器 
    │          ├─mapper                    # MybatisPlus映射
    │          ├─model                     # 数据库实体类 
    │          ├─result                    # 接口返回值统一封装
    │          ├─service                   # 服务类
    │          │  └─impl
    │          ├─token                     # token的工具类、控制类等
    │          └─utils                     # 公用方法
    └─resources
        ├─mapper                           # MybatisPlus 映射SQL
        └─templates                        # java代码生成器自定义
        ├─application.yml                  # springboot配置文件
        ├─application-dev.yml              # springboot配置测试环境文件
        ├─application-prod.yml             # springboot配置正式环境文件
        ├─logback-spring.xml               # 日志配置文件

```

## 配套VUE前端项目



vue3-elementplus-admin 基于本案例的API接口实现，可下载本地运行。

HTTPS  --> https://github.com/skingzxl5/vue3-elementplus-admin.git

SSH  -->  git@github.com:skingzxl5/vue3-elementplus-admin.git



## 项目运行Swagger地址



http://localhost:9223/doc.html

## 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request



