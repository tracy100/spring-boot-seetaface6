package com.lyc.service;

import com.lyc.pojo.FaceModel;
import com.lyc.pojo.FaceModelScore;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 人脸搜索
 */
public interface FaceSearchService {

    /**
     * 注册人脸信息
     *
     * @param faceImage
     * @return 返回人脸数据的ID
     */
    List<String> registerFace(MultipartFile faceImage) throws IOException;

    /**
     * 人脸搜索
     *
     * @param faceImage
     * @return 人脸搜索
     */
    List<FaceModelScore> faceSearch(MultipartFile faceImage) throws IOException;


}
