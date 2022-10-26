package com.lyc.controller;

import com.lyc.entities.FaceInfo;
import com.lyc.entities.FaceInfoBo;
import com.lyc.service.FaceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "pgsql 查询", protocols = "http")
@RestController
@RequestMapping(value = "/faceInfo")
public class FaceInfoController {

    @Resource
    private FaceInfoService faceInfoService;

    @ApiOperation(value = "通过ID获取单条数据", notes = "通过ID获取单条数据")
    @PostMapping("/getOne")
    public ResponseEntity<FaceInfo> getOne(Integer id) {

        Assert.notNull(id, "ID 不能为空");
        FaceInfo one = faceInfoService.getOne(id);

        return ResponseEntity.ok(one);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/insert")
    public ResponseEntity<Integer> insert(String path) throws Exception {

        Assert.notNull(path, "path 不能为空");
        int one = faceInfoService.save(path);

        return ResponseEntity.ok(one);
    }


    @ApiOperation(value = "直接上传文件", notes = "直接上传文件")
    @PostMapping("/uploadFile")
    public ResponseEntity<Integer> uploadFile(MultipartFile face, HttpServletRequest request) throws Exception {
        Assert.notNull(face, "上传人脸照片不能为空");

        int save = faceInfoService.save(face, request);

        return ResponseEntity.ok(save);
    }


    @ApiOperation(value = "查询前N个·", notes = "查询前N个")
    @PostMapping("/queryTopN")
    public ResponseEntity<List<FaceInfoBo>> queryTopN(MultipartFile face, Integer topN) {
        Assert.notNull(face, "上传人脸照片不能为空");
        List<FaceInfoBo> list = faceInfoService.queryTopN(face, topN);
        return ResponseEntity.ok(list);
    }
}
