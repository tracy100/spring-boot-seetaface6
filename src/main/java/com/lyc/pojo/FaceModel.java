package com.lyc.pojo;

import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;

import java.util.Arrays;

/**
 * 单个人脸数据
 */
public class FaceModel {

    /**
     * 注册时生成的Id
     */
    private String id;

    /**
     * 注册时图片文件名
     */
    private String fileName;

    /**
     * 注册时备注的内容
     */
    private String content;

    /**
     * 注册时间
     */
    private String createTime;

    /**
     * 人脸位置
     */
    private SeetaRect seetaRect;

    /**
     * 人脸关键点
     */
    private SeetaPointF[] pointFS;

    /**
     * 人脸向量特征数组
     */
    private float[] features;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public SeetaRect getSeetaRect() {
        return seetaRect;
    }

    public void setSeetaRect(SeetaRect seetaRect) {
        this.seetaRect = seetaRect;
    }

    public SeetaPointF[] getPointFS() {
        return pointFS;
    }

    public void setPointFS(SeetaPointF[] pointFS) {
        this.pointFS = pointFS;
    }

    public float[] getFeatures() {
        return features;
    }

    public void setFeatures(float[] features) {
        this.features = features;
    }


    @Override
    public String toString() {
        return "FaceModel{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", seetaRect=" + seetaRect +
                ", pointFS=" + Arrays.toString(pointFS) +
                ", features=" + Arrays.toString(features) +
                '}';
    }
}
