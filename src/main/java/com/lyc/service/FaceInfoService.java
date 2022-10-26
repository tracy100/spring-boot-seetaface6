package com.lyc.service;

import com.lyc.entities.FaceInfo;
import com.lyc.entities.FaceInfoBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FaceInfoService {

   FaceInfo getOne(Integer id);

   int save(FaceInfo faceInfo);

   int save(String path) throws Exception;

   int save(MultipartFile face, HttpServletRequest servletRequest) throws Exception;


   List<FaceInfo> getAll();

   List<FaceInfoBo> queryTopN(MultipartFile face, int topN);
}
