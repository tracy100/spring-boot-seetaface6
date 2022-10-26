package com.lyc.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import com.lyc.pojo.FaceModel;
import com.lyc.pojo.FaceModelScore;
import com.lyc.register.RegisterFace;
import com.lyc.service.FaceSearchService;
import com.seeta.proxy.FaceDetectorProxy;
import com.seeta.proxy.FaceLandmarkerProxy;
import com.seeta.proxy.FaceRecognizerProxy;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetafaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 人脸搜索
 */
@Service
public class FaceSearchServiceImpl implements FaceSearchService {

    @Autowired
    FaceDetectorProxy faceDetector;

    @Autowired
    FaceLandmarkerProxy faceLandmarker5;

    @Autowired
    FaceRecognizerProxy faceRecognizer;

    @Autowired
    RegisterFace registerFace;

    /**
     * 注册人脸信息
     *
     * @param faceImage
     * @return 注册人脸返回的ID
     */
    @Override
    public List<String> registerFace(MultipartFile faceImage) throws IOException {

        SeetaImageData seetaImageData = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
        String imgName = faceImage.getOriginalFilename();
        //接收一张图片上识别到的所有人脸
        List<FaceModel> faceModels = new ArrayList<>();
        try {
            SeetaRect[] detects = faceDetector.detect(seetaImageData);
            for (SeetaRect seetaRect : detects) {
                SeetaPointF[] pointFS = faceLandmarker5.mark(seetaImageData, seetaRect);
                float[] features = faceRecognizer.extract(seetaImageData, pointFS);
                //一个人脸的基本信息
                FaceModel faceModel = new FaceModel();
                faceModel.setFeatures(features);
                faceModel.setCreateTime(DateUtil.now());
                faceModel.setFileName(imgName);
                faceModel.setSeetaRect(seetaRect);
                faceModel.setPointFS(pointFS);
//                faceModel.setId(
//                        MD5.create().digestHex(Arrays.toString(features), Charset.defaultCharset()));
                faceModels.add(faceModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<String> register = registerFace.register(faceModels);
        return register;
    }

    /**
     * 搜索人脸信息
     *
     * @param faceImage
     * @return
     */
    @Override
    public List<FaceModelScore> faceSearch(MultipartFile faceImage) throws IOException {
        SeetaImageData seetaImageData = SeetafaceUtil.toSeetaImageData(ImageIO.read(faceImage.getInputStream()));
        List<FaceModelScore> res = null;
        try {
            SeetaRect[] detects = faceDetector.detect(seetaImageData);

            if (detects != null && detects.length > 0) {
                SeetaPointF[] pointFS = faceLandmarker5.mark(seetaImageData, detects[0]);
                float[] features = faceRecognizer.extract(seetaImageData, pointFS);
                res = registerFace.search(features);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
