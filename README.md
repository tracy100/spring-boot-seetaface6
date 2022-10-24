# spring-boot-seetaface6

#### 介绍
spring boot 人脸识别
基于seetaface6SDK(https://gitee.com/crazy-of-pig/seeta-sdk-platform.git)项目打成的jar,配置人脸识别Bean做成spring boot项目。

#### 演示真假人脸识别
1.  spoof为攻击人脸，real为真人脸
    ![Image text](https://gitee.com/crazy-of-pig/seeta-sdk-platform/raw/master/img/%E6%94%BB%E5%87%BB%E4%BA%BA%E8%84%B8%E6%A3%80%E6%B5%8B.jpg)

#### 软件架构
spring boot
seetaface6SDK

#### 使用说明
1.  正常运行项目，下载seetaface6的模型文件，并将路径配置到application.yml里面
2.  打开url: http://localhost:8018/seetaface6/swagger-ui.html  进行调试
