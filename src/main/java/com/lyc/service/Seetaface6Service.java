package com.lyc.service;

import com.lyc.entities.FaceInfo;
import com.seeta.proxy.GenderPredictorProxy;
import com.seeta.proxy.MaskDetectorProxy;
import com.seeta.sdk.FaceAntiSpoofing;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Seetaface6Service {

    /**
     * 攻击人脸检测
     *
     * @param faceImage
     * @return List<FaceAntiSpoofing.Status>
     */
    List<FaceAntiSpoofing.Status> faceAntiSpoofing(MultipartFile faceImage);

    /**
     * 带口罩人脸检测
     *
     * @param faceImage
     * @return 人是否带口罩
     */
    List<MaskDetectorProxy.MaskItem> maskDetector(MultipartFile faceImage);

    /**
     * 人脸性别判断
     *
     * @param faceImage
     * @return List<GenderPredictorProxy.GenderItem> 多个人脸的性别
     */
    List<GenderPredictorProxy.GenderItem> genderPredictor(MultipartFile faceImage);

    /**
     * 人脸年龄判断
     *
     * @param faceImage
     * @return List<Integer> 多个人脸的年龄
     */
    List<Integer> agePredictor(MultipartFile faceImage);

    /**
     * 人脸对比，1：1
     *
     * @param face1
     * @param face2
     * @return Float 分数 0~1
     */
    Float faceRecognizer(MultipartFile face1, MultipartFile face2);

    float[] getOneFaceFeature(MultipartFile faceImage);

    List<FaceInfo> getFaceInfo(MultipartFile face, String path) throws Exception;

    List<FaceInfo> getFaceInfo(File face) throws Exception;
}
