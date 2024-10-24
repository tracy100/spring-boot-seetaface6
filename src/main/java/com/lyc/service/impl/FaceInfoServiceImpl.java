package com.lyc.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyc.entities.FaceInfo;
import com.lyc.entities.FaceInfoBo;
import com.lyc.exception.Seetaface6Exception;
import com.lyc.mapper.FaceInfoMapper;
import com.lyc.service.FaceInfoService;
import com.lyc.service.FileManageService;
import com.lyc.service.Seetaface6Service;
import com.qiwenshare.ufop.operation.upload.domain.UploadFileResult;
import org.apache.ibatis.executor.BatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class FaceInfoServiceImpl implements FaceInfoService {

    Logger logger = LoggerFactory.getLogger(FaceInfoServiceImpl.class);

    @Resource
    FaceInfoMapper faceInfoMapper;

    /**
     * 人脸解释器
     */
    @Resource
    Seetaface6Service seetaface6Service;

    /**
     * 需要上传文件的话可以用这个
     */
    @Resource
    FileManageService fileManageService;

    @Override
    public FaceInfo getOne(Integer id) {

        return faceInfoMapper.selectById(id);
    }

    @Override
    public int save(FaceInfo faceInfo) {

        return faceInfoMapper.insert(faceInfo);
    }

    @Override
    public int save(String path) throws Exception {

        File file = new File(path);

        boolean exist = FileUtil.exist(file);
        if (exist) {
            // 一张照片可能有多个人脸
            List<FaceInfo> faceInfoList = seetaface6Service.getFaceInfo(file);
            if (faceInfoList != null && !faceInfoList.isEmpty()) {

                // 批量保存
                List<BatchResult> insert = faceInfoMapper.insert(faceInfoList);
                logger.info("insert： {}", insert);
                return insert.size();
            }
            return 0;
        } else {
            logger.error("文件不存在： {}", file.getAbsolutePath());
        }
        return 0;
    }

    @Override
    public int save(MultipartFile face, HttpServletRequest servletRequest) throws Exception {
        // 上传到本地文件服务
        List<UploadFileResult> uploadFileResults = fileManageService.uploadFile(servletRequest);
        if (uploadFileResults != null && !uploadFileResults.isEmpty()) {
            UploadFileResult uploadFileResult = uploadFileResults.get(0);
            List<FaceInfo> faceInfo = seetaface6Service.getFaceInfo(face, uploadFileResult.getFileUrl());
            if (faceInfo != null && !faceInfo.isEmpty()) {
                // 批量保存
                List<BatchResult> insert = faceInfoMapper.insert(faceInfo);
                logger.info("insert： {}", insert);
                return insert.size();
            }
        }
        return 0;
    }

    @Override
    public List<FaceInfo> getAll() {
        return faceInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<FaceInfoBo> queryTopN(MultipartFile face, int topN) throws Seetaface6Exception {

        float[] oneFaceFeature = seetaface6Service.faceRecognizer(face);

        if (oneFaceFeature != null && oneFaceFeature.length > 0) {
            // 设置参数，session范围内有效
            faceInfoMapper.setEfSearch();
            // 执行向量查询
            List<FaceInfoBo> list = faceInfoMapper.queryByFeatures(oneFaceFeature, topN);

            // 这里可以做一下其他业务操作
            // .....

            return list;
        }

        return null;
    }


}
