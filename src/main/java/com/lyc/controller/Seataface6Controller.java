package com.lyc.controller;

import com.lyc.service.Seetaface6Service;
import com.seeta.proxy.GenderPredictorProxy;
import com.seeta.proxy.MaskDetectorProxy;
import com.seeta.sdk.FaceAntiSpoofing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "seetaface6人脸识别API", protocols = "http")
@RestController
@RequestMapping(value = "/seetaface6")
public class Seataface6Controller {

    @Resource
    Seetaface6Service seetaface6Service;

    @ApiOperation(value = "攻击人脸检测", notes = "攻击人脸检测")
    @PostMapping("/faceAntiSpoofing")
    public ResponseEntity<List<FaceAntiSpoofing.Status>> faceAntiSpoofing(MultipartFile faceImage) {

        Assert.notNull(faceImage, "上传人脸照片不能为空");
        List<FaceAntiSpoofing.Status> statuses = seetaface6Service.faceAntiSpoofing(faceImage);
        return ResponseEntity.ok(statuses);
    }

    @ApiOperation(value = "识别人脸是否戴口罩", notes = "识别人脸是否戴口罩")
    @PostMapping("/maskDetector")
    public ResponseEntity<List<MaskDetectorProxy.MaskItem>> maskDetector(MultipartFile faceImage) {
        Assert.notNull(faceImage, "上传人脸照片不能为空");
        List<MaskDetectorProxy.MaskItem> list = seetaface6Service.maskDetector(faceImage);

        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "识别人脸照片性别", notes = "识别人脸照片性别")
    @PostMapping("/genderPredictor")
    public ResponseEntity<List<GenderPredictorProxy.GenderItem>> genderPredictor(MultipartFile faceImage) {
        Assert.notNull(faceImage, "上传人脸照片不能为空");
        List<GenderPredictorProxy.GenderItem> list = seetaface6Service.genderPredictor(faceImage);
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "识别人脸照片年龄", notes = "识别人脸照片年龄")
    @PostMapping("/agePredictor")
    public ResponseEntity<List<Integer>> agePredictor(MultipartFile faceImage) {
        Assert.notNull(faceImage, "上传人脸照片不能为空");
        List<Integer> ages = seetaface6Service.agePredictor(faceImage);
        return ResponseEntity.ok(ages);
    }

    @ApiOperation(value = "人脸相似度1:1", notes = "人脸相似度1:1")
    @PostMapping("/faceRecognizer")
    public ResponseEntity<Float> faceRecognizer(MultipartFile face1, MultipartFile face2) {
        Assert.notNull(face1, "上传人脸照片1不能为空");
        Assert.notNull(face2, "上传人脸照片2不能为空");
        Float calculateSimilarity = seetaface6Service.faceRecognizer(face1, face2);
        return ResponseEntity.ok(calculateSimilarity);
    }
}
