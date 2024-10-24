package com.lyc.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyc.typeHandler.DoubleArray2DTypeHandler;
import com.lyc.typeHandler.VectorTypeHandler;

import java.io.Serializable;
import java.util.Date;


@TableName(value = "seeta_face_info", autoResultMap = true)
public class FaceInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 文件名
    private String fileName;

    // 文件路径
    private String filePath;

    // 人脸向量特征
    @TableField(typeHandler = VectorTypeHandler.class)
    private float[] features;

    // SeetaRect 人脸在图片中的位置和长宽
    public Integer x;
    public Integer y;
    public Integer width;
    public Integer height;

    // 人脸关键点，相对于原始图片的位置
    @TableField(typeHandler = DoubleArray2DTypeHandler.class)
    private double[][] points;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    public double[][] getPoints() {
        return points;
    }

    public void setPoints(double[][] points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public float[] getFeatures() {
        return features;
    }

    public void setFeatures(float[] features) {
        this.features = features;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}