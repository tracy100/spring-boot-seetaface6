package com.lyc.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.lyc.entities.FaceInfo;
import com.lyc.exception.Seetaface6Exception;
import com.lyc.service.Seetaface6Service;
import com.seeta.proxy.*;
import com.seeta.sdk.FaceAntiSpoofing;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetafaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * seetaface6SDK API测试
 */
@Service
public class Seetaface6ServiceImpl implements Seetaface6Service {

    Logger logger = LoggerFactory.getLogger(Seetaface6ServiceImpl.class);

    @Resource
    FaceDetectorProxy faceDetector;

    @Resource
    FaceLandmarkerProxy faceLandmarker5;

    @Resource
    FaceRecognizerProxy faceRecognizer;

    @Resource
    AgePredictorProxy agePredictor;

    @Resource
    GenderPredictorProxy genderPredictor;

    @Resource
    MaskDetectorProxy maskDetector;

    @Resource
    FaceAntiSpoofingProxy faceAntiSpoofing;

    /**
     * 攻击人脸检测
     *
     * @param faceImage 人脸图片
     * @return List<FaceAntiSpoofing.Status>
     */
    @Override
    public List<FaceAntiSpoofing.Status> faceAntiSpoofing(MultipartFile faceImage) throws Seetaface6Exception {
        List<FaceAntiSpoofing.Status> list = new ArrayList<>();
        try {
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
            SeetaRect[] detects = faceDetector.detect(image);
            for (SeetaRect seetaRect : detects) {
                SeetaPointF[] pointFS = faceLandmarker5.mark(image, seetaRect);
                FaceAntiSpoofing.Status predict = faceAntiSpoofing.predict(image, seetaRect, pointFS);
                list.add(predict);
            }
        } catch (Exception e) {
            logger.error("攻击人脸检测报错", e);
            throw new Seetaface6Exception(e);
        }
        return list;
    }

    /**
     * 带口罩人脸检测
     *
     * @param faceImage 人脸图片
     * @return 人是否带口罩
     */
    @Override
    public List<MaskDetectorProxy.MaskItem> maskDetector(MultipartFile faceImage) throws Seetaface6Exception {
        List<MaskDetectorProxy.MaskItem> list = new ArrayList<>();
        try {
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
            SeetaRect[] detects = faceDetector.detect(image);
            for (SeetaRect seetaRect : detects) {
                MaskDetectorProxy.MaskItem detect = maskDetector.detect(image, seetaRect);
                list.add(detect);
            }
        } catch (Exception e) {
            logger.error("带口罩人脸检测报错", e);
            throw new Seetaface6Exception(e);
        }
        return list;
    }

    /**
     * 人脸性别判断
     *
     * @param faceImage 人脸图片
     * @return List<GenderPredictorProxy.GenderItem> 多个人脸的性别
     */
    @Override
    public List<GenderPredictorProxy.GenderItem> genderPredictor(MultipartFile faceImage) throws Seetaface6Exception {
        List<GenderPredictorProxy.GenderItem> list = new ArrayList<>();
        try {
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
            SeetaRect[] detects = faceDetector.detect(image);
            for (SeetaRect seetaRect : detects) {
                //face_landmarker_pts5 根据这个来的
                SeetaPointF[] pointFS = faceLandmarker5.mark(image, seetaRect);
                list.add(genderPredictor.predictGenderWithCrop(image, pointFS));
            }
        } catch (Exception e) {
            logger.error("人脸性别判断检测报错", e);
            throw new Seetaface6Exception(e);
        }
        return list;
    }

    /**
     * 人脸年龄判断
     *
     * @param faceImage 人脸图片
     * @return List<Integer> 多个人脸的年龄
     */
    @Override
    public List<Integer> agePredictor(MultipartFile faceImage) throws Seetaface6Exception {
        List<Integer> ageArray = new ArrayList<>();
        try {
            BufferedImage srcImage = ImageIO.read(faceImage.getInputStream());
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(srcImage);
            //检测人脸，识别到人脸
            SeetaRect[] detects = faceDetector.detect(image);
            for (SeetaRect seetaRect : detects) {
                //人脸关键点定位
                SeetaPointF[] pointFS = faceLandmarker5.mark(image, seetaRect);
                //识别年龄
                int age = agePredictor.predictAgeWithCrop(image, pointFS);
                ageArray.add(age);

            }
        } catch (Exception e) {
            logger.error("人脸年龄判断报错", e);
            throw new Seetaface6Exception(e);
        }
        return ageArray;
    }

    /**
     * 人脸对比，1：1
     *
     * @param face1 人脸1
     * @param face2 人脸2
     * @return Float 分数 0~1
     */
    @Override
    public Float faceRecognizer(MultipartFile face1, MultipartFile face2) throws Seetaface6Exception {
        float[] features1 = null;
        float[] features2 = null;
        try {
            SeetaImageData image1 = SeetafaceUtil.toSeetaImageData(ImageIO.read(face1.getInputStream()));
            SeetaImageData image2 = SeetafaceUtil.toSeetaImageData(ImageIO.read(face2.getInputStream()));
            SeetaRect[] detects1 = faceDetector.detect(image1);
            for (SeetaRect seetaRect : detects1) {
                SeetaPointF[] pointFS = faceLandmarker5.mark(image1, seetaRect);
                features1 = faceRecognizer.extract(image1, pointFS);
            }
            SeetaRect[] detects2 = faceDetector.detect(image2);
            for (SeetaRect seetaRect : detects2) {
                SeetaPointF[] pointFS = faceLandmarker5.mark(image2, seetaRect);
                features2 = faceRecognizer.extract(image2, pointFS);
            }
        } catch (Exception e) {
            logger.error("人脸对比，1：1报错", e);
            throw new Seetaface6Exception(e);
        }
        float calculateSimilarity = 0.00F;
        if (features1 != null && features2 != null) {
            calculateSimilarity = faceRecognizer.cosineSimilarity(features1, features2);
        }
        return calculateSimilarity;
    }

    @Override
    public float[] faceRecognizer(MultipartFile face) throws Seetaface6Exception {
        float[] features = null;
        try {
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(face.getInputStream()));
            SeetaRect[] detects = faceDetector.detect(image);
            if (detects != null && detects.length > 0) {
                SeetaPointF[] pointFS = faceLandmarker5.mark(image, detects[0]);
                features = faceRecognizer.extract(image, pointFS);
            }
        } catch (Exception e) {
            throw new Seetaface6Exception(e);
        }
        return features;
    }

    @Override
    public List<FaceInfo> getFaceInfo(MultipartFile face, String path) throws Exception {

        return getFaceInfo(face.getInputStream(), face.getOriginalFilename(), path);
    }

    @Override
    public List<FaceInfo> getFaceInfo(File face) throws Exception {

        return getFaceInfo(FileUtil.getInputStream(face), face.getName(), face.getAbsolutePath());
    }

    public List<FaceInfo> getFaceInfo(InputStream face, String name, String path) throws Exception {

        List<FaceInfo> list = null;
        SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(face));
        SeetaRect[] detects = faceDetector.detect(image);
        if (detects != null && detects.length > 0) {
            list = new ArrayList<>();

            for (SeetaRect seetaRect : detects) {
                FaceInfo faceInfo = new FaceInfo();

                SeetaPointF[] pointFs = faceLandmarker5.mark(image, seetaRect);
                float[] features = faceRecognizer.extract(image, pointFs);

                // 创建时间
                DateTime date = DateUtil.date();
                faceInfo.setCreateTime(date);
                faceInfo.setUpdateTime(date);

                // 人脸向量
                faceInfo.setFeatures(features);

                // 文件名
                faceInfo.setFileName(name);

                // 文件路径
                faceInfo.setFilePath(path);

                // 人脸在原图片中的坐标和高宽
                faceInfo.setX(seetaRect.x);
                faceInfo.setY(seetaRect.y);
                faceInfo.setHeight(seetaRect.height);
                faceInfo.setWidth(seetaRect.width);

                // 人脸关键点 5点
                double[][] ponitArr = new double[5][2];
                for (int i = 0; i < pointFs.length; i++) {
                    SeetaPointF pointF = pointFs[i];
                    double[] ponit = new double[2];
                    ponit[0] = pointF.x;
                    ponit[1] = pointF.y;
                    ponitArr[i] = ponit;
                }
                faceInfo.setPoints(ponitArr);
                list.add(faceInfo);
            }

        }
        return list;
    }

}
