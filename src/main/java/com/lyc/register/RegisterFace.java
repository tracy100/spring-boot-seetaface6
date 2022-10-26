package com.lyc.register;

import com.lyc.pojo.FaceModel;
import com.lyc.pojo.FaceModelScore;

import java.util.List;

/**
 * 注册人脸的抽象方法
 */
public interface RegisterFace {

    List<String> register(List<FaceModel> faceModels);

    String register(FaceModel faceModel);

    List<FaceModelScore> search(float[] features, int topN, float minimum_score);

    List<FaceModelScore> search(float[] features);

}
