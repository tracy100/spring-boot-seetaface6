# spring-boot-seetaface6 spring boot 人脸识别

#### 介绍

1.  基于seetaface6SDK(https://gitee.com/crazy-of-pig/seeta-sdk-platform.git)
    seetaface6SDK人脸识别器是本项目的基础
2.  将各种人脸识别器配置成spring bean 做成spring boot项目。
3.  建议先看seetaface6SDK项目。
4.  用pgvector做向量数据库，结合mybatis plus操作向量数据类型
5.  创建database表空间，配置yml中pgvectord的datasource参数即可运行项目，自动建表和创建索引

#### 演示API
1.  几种常见api展示，1:1，1:N
    ![Image text](https://gitee.com/crazy-of-pig/spring-boot-seetaface6/raw/master/img/api.png)
2.  将项目目录介绍
    ![Image text](https://gitee.com/crazy-of-pig/spring-boot-seetaface6/raw/master/img/menu.png)

#### 软件架构
postgreSql(pgvector), spring-boot, mybatis-plus
seetaface6SDK

#### 使用说明
1.  正常运行项目，下载seetaface6的模型文件，并将路径配置到application.yml里面
2.  打开url: http://localhost:8018/seetaface6/swagger-ui.html  进行调试
