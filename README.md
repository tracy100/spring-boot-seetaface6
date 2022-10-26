# spring-boot-seetaface6 spring boot 人脸识别

#### 介绍

1.  基于seetaface6SDK(https://gitee.com/crazy-of-pig/seeta-sdk-platform.git)
2.  将各种人脸识别器配置成spring bean 做成spring boot项目。
3.  window10 环境需要安装 visual_studio；linux 环境需要安装 cmake-3.12.4.tar.gz。 详细情况请先看seetaface6SDK项目

#### 演示API
1.  几种常见api展示，1:1，1:N
    ![Image text](https://gitee.com/crazy-of-pig/spring-boot-seetaface6/raw/master/img/api.png)

#### 软件架构
spring boot
seetaface6SDK

#### 使用说明
1.  正常运行项目，下载seetaface6的模型文件，并将路径配置到application.yml里面
2.  打开url: http://localhost:8018/seetaface6/swagger-ui.html  进行调试
