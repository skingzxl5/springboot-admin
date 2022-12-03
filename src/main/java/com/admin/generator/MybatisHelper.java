package com.admin.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skin
 * @Date 2022-10-05
 * @Description: 根据表结构自动生成代码
 *
 */

public class MybatisHelper {
    public static void main(String[] args)  {

        final String prefixClass = "";
        final String[] tableList = {"basice_menu","basice_menu_role","basice_role","basice_role_user"};

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(".\\src\\main\\java");
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setAuthor("Skin");
        gc.setMapperName(prefixClass+"%sMapper");
        gc.setXmlName(prefixClass+"%sMapper");
        gc.setServiceName(prefixClass+"%sService");
        gc.setServiceImplName(prefixClass+"%sServiceImpl");
        gc.setControllerName(prefixClass+"%sController");
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://rm-bp1l9tml81r1i5i18xo.mysql.rds.aliyuncs.com:3306/sys_admin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("sys_admin_user");
        dsc.setPassword("admin852!");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.admin");
        pc.setController("controller" );
        pc.setService("service" );
        pc.setServiceImpl("service.impl");
        pc.setEntity("model" );
        pc.setMapper("mapper" );
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return ".\\src\\main\\resources" + "/mapper/" + tableInfo.getEntityName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig tempCfg = new TemplateConfig();

        tempCfg.setEntity("/templates/entity.java");
        tempCfg.setXml(null);
        mpg.setTemplate(tempCfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableList);
        strategy.setEntityColumnConstant(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}