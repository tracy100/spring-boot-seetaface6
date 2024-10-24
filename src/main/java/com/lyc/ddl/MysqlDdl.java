package com.lyc.ddl;

import com.baomidou.mybatisplus.extension.ddl.IDdl;
import com.baomidou.mybatisplus.extension.ddl.history.IDdlGenerator;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * mybatis 数据库脚本管理工具，自动执行db/seeta_face_info.sql脚本。
 * 如果加字段，可以另外加一个脚本放到数组里面
 */
@Component
public class MysqlDdl implements IDdl {


    Logger logger = LoggerFactory.getLogger(MysqlDdl.class);
    @Autowired
    private DataSource dataSource;

    @Override
    public void runScript(Consumer<DataSource> consumer) {
        consumer.accept(this.dataSource);
    }

    @Override
    public IDdlGenerator getDdlGenerator() {
        return IDdl.super.getDdlGenerator();
    }

    /**
     * 获取要执行的SQL脚本文件列表
     */
    @Override
    public List<String> getSqlFiles() {

        logger.info("获取要执行的SQL脚本文件列表");
        List<String> list = Lists.newArrayList();
        // 拓展vector 插件
        list.add("db/extension_vector.sql");
        // 建表语句和测试数据
        list.add("db/seeta_face_info.sql");
        // 创建向量索引
        list.add("db/create_features_hnsw_index.sql");
        return list;
    }
}

// 切换到mysql从库，执行SQL脚本
//ShardingKey.change("mysqlt2");
//ddlScript.run(new StringReader("DELETE FROM user;\n" +
//        "INSERT INTO user (id, username, password, sex, email) VALUES\n" +
//        "(20, 'Duo', '123456', 0, 'Duo@baomidou.com');"));