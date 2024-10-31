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


Dockerfile:
``` 
# 使用一个基础镜像，这里以官方的Java镜像为例
FROM openjdk:8

# 设置作者信息
LABEL maintainer="linyc1993@outlook.com"

# 更新apt-get源，并安装libgomp1库
RUN apt-get update && \
   apt-get install -y --no-install-recommends libgomp1 && \
   rm -rf /var/lib/apt/lists/*

# 创建数据目录
RUN mkdir -p /data/app && mkdir -p /data/models

# 将JAR包复制到容器内的/data/app目录
COPY spring-boot-seetaface6-1.0.0.jar /data/app/spring-boot-seetaface6-1.0.0.jar

# 将模型文件复制到容器内的/data/models目录
COPY ./*.csta /data/models/

# 设置默认的工作目录
WORKDIR /data/app

# 定义运行时执行的命令，这里假设JAR包是可执行的
CMD ["java", "-jar", "spring-boot-seetaface6-1.0.0.jar"]
```