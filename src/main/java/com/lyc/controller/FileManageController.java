package com.lyc.controller;

import com.lyc.service.FileManageService;
import com.qiwenshare.ufop.operation.upload.domain.UploadFileResult;
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
import java.io.IOException;
import java.util.List;

@Api(value = "文件管理, 测试文件上传功能", protocols = "文件管理")
@RestController
@RequestMapping(value = "/fileManage")
public class FileManageController {

    @Resource
    FileManageService fileManageService;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/uploadFile")
    public ResponseEntity<Integer> uploadFile(MultipartFile faceImage, HttpServletRequest request) throws IOException {

        Assert.notNull(faceImage, "faceImage 不能为空");

        List<UploadFileResult> uploadFileResults = fileManageService.uploadFile(request);

        return ResponseEntity.ok(uploadFileResults.size());
    }
}
