package com.lyc.service.impl;

import com.lyc.service.Seetaface6Service;
import com.seeta.proxy.*;
import com.seeta.sdk.FaceAntiSpoofing;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetafaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * seetaface6SDK API测试
 */
@Service
public class Seetaface6ServiceImpl implements Seetaface6Service {

    @Autowired
    FaceDetectorProxy faceDetector;

    @Autowired
    FaceLandmarkerProxy faceLandmarker5;

    @Autowired
    FaceRecognizerProxy faceRecognizer;

    @Autowired
    AgePredictorProxy agePredictor;

    @Autowired
    GenderPredictorProxy genderPredictor;

    @Autowired
    MaskDetectorProxy maskDetector;

    @Autowired
    FaceAntiSpoofingProxy faceAntiSpoofing;

    /**
     * 攻击人脸检测
     *
     * @param faceImage
     * @return List<FaceAntiSpoofing.Status>
     */
    @Override
    public List<FaceAntiSpoofing.Status> faceAntiSpoofing(MultipartFile faceImage) {
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 带口罩人脸检测
     *
     * @param faceImage
     * @return 人是否带口罩
     */
    @Override
    public List<MaskDetectorProxy.MaskItem> maskDetector(MultipartFile faceImage) {
        List<MaskDetectorProxy.MaskItem> list = new ArrayList<>();
        try {
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
            SeetaRect[] detects = faceDetector.detect(image);
            for (SeetaRect seetaRect : detects) {
                MaskDetectorProxy.MaskItem detect = maskDetector.detect(image, seetaRect);
                list.add(detect);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 人脸性别判断
     *
     * @param faceImage
     * @return List<GenderPredictorProxy.GenderItem> 多个人脸的性别
     */
    @Override
    public List<GenderPredictorProxy.GenderItem> genderPredictor(MultipartFile faceImage) {
        List<GenderPredictorProxy.GenderItem> list = new ArrayList<>();
        try {
            SeetaImageData image = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
            SeetaRect[] detects = faceDetector.detect(image);
            for (SeetaRect seetaRect : detects) {
                //face_landmarker_pts5 根据这个来的
                SeetaPointF[] pointFS = faceLandmarker5.mark(image, seetaRect);
                list.add(genderPredictor.predictGenderWithCrop(image, pointFS));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 人脸年龄判断
     *
     * @param faceImage
     * @return List<Integer> 多个人脸的年龄
     */
    @Override
    public List<Integer> agePredictor(MultipartFile faceImage) {
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
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ageArray;
    }

    /**
     * 人脸对比，1：1
     *
     * @param face1
     * @param face2
     * @return Float 分数 0~1
     */
    @Override
    public Float faceRecognizer(MultipartFile face1, MultipartFile face2) {
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        float calculateSimilarity = 0.00F;
        if (features1 != null && features2 != null) {
            calculateSimilarity = faceRecognizer.cosineSimilarity(features1, features2);
        }
        return calculateSimilarity;
    }
}
