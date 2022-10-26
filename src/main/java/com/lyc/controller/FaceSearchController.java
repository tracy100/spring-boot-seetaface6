package com.lyc.controller;

import com.lyc.pojo.FaceModelScore;
import com.lyc.service.FaceSearchService;
import com.seeta.sdk.FaceAntiSpoofing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 人脸搜索 1：N
 * 将底库人脸头像向量特征注册到内存中，项目重启后注册的人脸将失效
 * 可以将人脸向量特征保存到数据库，项目启动时加载到内存
 */
@Api(value = "人脸搜索API,1:N 搜索", protocols = "http")
@RestController
@RequestMapping(value = "/faceSearch")
public class FaceSearchController {

    @Autowired
    FaceSearchService faceSearchService;

    @ApiOperation(value = "人脸搜索底库注册", notes = "人脸搜索底库注册")
    @PostMapping("/faceRegister")
    public ResponseEntity<List<String>> faceRegister(MultipartFile faceImage) throws IOException {

        Assert.notNull(faceImage, "上传人脸照片不能为空");
        List<String> strings = faceSearchService.registerFace(faceImage);

        return ResponseEntity.ok(strings);
    }

    @ApiOperation(value = "人脸搜索 1:N (只取第一张头像搜索)", notes = "人脸搜索 1:N (只取第一张头像搜索)")
    @PostMapping("/faceSearch")
    public ResponseEntity<List<FaceModelScore>> faceSearch(MultipartFile faceImage) throws IOException {

        Assert.notNull(faceImage, "上传人脸照片不能为空");

        List<FaceModelScore> faceModelScores = faceSearchService.faceSearch(faceImage);
        return ResponseEntity.ok(faceModelScores);
    }

}
