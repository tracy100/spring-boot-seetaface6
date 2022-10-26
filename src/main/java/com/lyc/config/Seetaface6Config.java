package com.lyc.config;

import com.lyc.contant.ModelPath;
import com.seeta.pool.SeetaConfSetting;
import com.seeta.proxy.*;
import com.seeta.sdk.SeetaDevice;
import com.seeta.sdk.SeetaModelSetting;
import com.seeta.sdk.util.LoadNativeCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * seetaface6识别器 连接池代理配置
 */
@Configuration
public class Seetaface6Config {

    /*
      加载dll
     */
    static {
        LoadNativeCore.LOAD_NATIVE(SeetaDevice.SEETA_DEVICE_GPU);
    }

    private static Logger logger = LoggerFactory.getLogger(Seetaface6Config.class);

    @Autowired
    ModelPath modelPath;

    /**
     * 人脸检测器
     *
     * @return FaceDetectorProxy
     */
    @Bean
    public FaceDetectorProxy faceDetector() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("人脸识别检测器，模型文件路径： {}", Arrays.toString(modelPath.getFace_detector()));
        }
        //人脸识别检测器对象池配置
        SeetaConfSetting detectorPoolSetting = new SeetaConfSetting(
                new SeetaModelSetting(0, modelPath.getFace_detector(), SeetaDevice.SEETA_DEVICE_AUTO));
        //人脸检测器对象池代理 ， spring boot可以用FaceDetectorProxy来配置Bean
        FaceDetectorProxy faceDetectorProxy = new FaceDetectorProxy(detectorPoolSetting);
        return faceDetectorProxy;
    }

    /**
     * 人脸关键点定位器
     *
     * @return
     */
    @Bean
    public FaceLandmarkerProxy faceLandmarker5() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("人脸关键点定位器，模型文件路径： {}", Arrays.toString(modelPath.getFace_landmarker_pts5()));
        }
        //人脸关键点定位器对象池配置
        SeetaConfSetting faceLandmarkerPoolSetting = new SeetaConfSetting(
                new SeetaModelSetting(0, modelPath.getFace_landmarker_pts5(), SeetaDevice.SEETA_DEVICE_AUTO));
        //人脸关键点定位器对象池代理
        FaceLandmarkerProxy faceLandmarkerProxy = new FaceLandmarkerProxy(faceLandmarkerPoolSetting);
        return faceLandmarkerProxy;
    }

    /**
     * 人脸特征提取，人脸比对
     *
     * @return
     */
    @Bean
    public FaceRecognizerProxy faceRecognizer() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("人脸特征提取人脸比对器，模型文件路径： {}", Arrays.toString(modelPath.getFace_recognizer()));
        }
        SeetaConfSetting faceRecognizerPoolSetting = new SeetaConfSetting(
                new SeetaModelSetting(0, modelPath.getFace_recognizer(), SeetaDevice.SEETA_DEVICE_AUTO));
        //人脸特征提取，人脸比对 执行器
        FaceRecognizerProxy faceRecognizerProxy = new FaceRecognizerProxy(faceRecognizerPoolSetting);
        return faceRecognizerProxy;
    }


    /**
     * 人脸年龄检测器
     *
     * @return
     */
    @Bean
    public GenderPredictorProxy genderPredictor() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("性别识别器，模型文件路径： {}", Arrays.toString(modelPath.getGender_predictor()));
        }

        //性别识别器对象池配置
        SeetaConfSetting genderPredictorPoolSetting = new SeetaConfSetting(
                new SeetaModelSetting(0, modelPath.getGender_predictor(), SeetaDevice.SEETA_DEVICE_AUTO));
        //性别识别器对象池代理 ， spring boot可以用GenderPredictorProxy来配置Bean
        GenderPredictorProxy genderPredictorProxy = new GenderPredictorProxy(genderPredictorPoolSetting);
        return genderPredictorProxy;
    }

    /**
     * 人脸年龄检测器
     *
     * @return
     */
    @Bean
    public AgePredictorProxy agePredictor() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("人脸年龄检测器，模型文件路径： {}", Arrays.toString(modelPath.getAge_predictor()));
        }
        //人脸年龄检测器对象池配置
        SeetaConfSetting agePredictorPoolSetting = new SeetaConfSetting(
                new SeetaModelSetting(0, modelPath.getAge_predictor(), SeetaDevice.SEETA_DEVICE_AUTO));
        //人脸年龄检测器对象池代理
        AgePredictorProxy agePredictorProxy = new AgePredictorProxy(agePredictorPoolSetting);
        return agePredictorProxy;
    }

    /**
     * 人脸戴口罩检测器
     *
     * @return
     */
    @Bean
    public MaskDetectorProxy maskDetector() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("人脸戴口罩检测器，模型文件路径： {}", Arrays.toString(modelPath.getMask_detector()));
        }
        //口罩检测器对象池配置
        SeetaConfSetting maskDetectorPoolSetting = new SeetaConfSetting(
                new SeetaModelSetting(0, modelPath.getMask_detector(), SeetaDevice.SEETA_DEVICE_AUTO));
        //口罩检测器对象池代理 ， spring boot可以用MaskDetectorProxy来配置Bean
        MaskDetectorProxy maskDetectorProxy = new MaskDetectorProxy(maskDetectorPoolSetting);
        return maskDetectorProxy;
    }


    /**
     * 攻击人脸检测器
     *
     * @return
     */
    @Bean
    public FaceAntiSpoofingProxy faceAntiSpoofing() throws FileNotFoundException {
        if (logger.isDebugEnabled()) {
            logger.debug("攻击人脸检测器，模型文件路径：模型一 {} ，模型二 {}", modelPath.getFas_first(), modelPath.getFas_second());
        }
        //攻击人脸检测器对象池配置
        SeetaConfSetting faceAntiSpoofingSetting = new SeetaConfSetting(
                new SeetaModelSetting(0,
                        new String[]{modelPath.getFas_first(), modelPath.getFas_second()},
                        SeetaDevice.SEETA_DEVICE_AUTO));
        //攻击人脸检测器对象池代理
        FaceAntiSpoofingProxy faceAntiSpoofingProxy = new FaceAntiSpoofingProxy(faceAntiSpoofingSetting);
        return faceAntiSpoofingProxy;
    }

}
