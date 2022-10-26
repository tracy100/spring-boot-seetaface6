package com.lyc.register;

import cn.hutool.core.util.IdUtil;
import com.lyc.contant.FaceRegisterConfig;
import com.lyc.pojo.FaceModel;
import com.lyc.pojo.FaceModelScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * 通过缓存注册人脸
 */
public class CacheRegisterFace implements RegisterFace {

    private static Logger logger = LoggerFactory.getLogger(CacheRegisterFace.class);

    private FaceRegisterConfig faceRegisterConfig;

    static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static Map<String, FaceModel> faceCache = new HashMap<>();

    public CacheRegisterFace(FaceRegisterConfig faceRegisterConfig) {
        this.faceRegisterConfig = faceRegisterConfig;
    }

    @Override
    public List<String> register(List<FaceModel> faceModels) {
        List<String> ids = new ArrayList<>();
        if (faceModels.size() > 0) {
            for (FaceModel faceModel : faceModels) {
                String id = register(faceModel);
                ids.add(id);
            }
        }
        return ids;
    }

    @Override
    public String register(FaceModel faceModel) {
        //向量数据 长度不等于512 或是1024的不允许注册
        if (faceModel.getFeatures() != null
                && (faceModel.getFeatures().length == 512
                || faceModel.getFeatures().length == 1024)) {
            String id = null;
            if (faceCache.size() < faceRegisterConfig.getMax_face()) {
                lock.writeLock().lock();
                try {
                    if (faceCache.size() < faceRegisterConfig.getMax_face()) {
                        //根据文件名和向量特征生成ID
                        id = IdUtil.objectId();
                        faceModel.setId(id);
                        faceCache.put(id, faceModel);
                    } else {
                        logger.warn("缓存数据已满，不能再注册人脸了！ {}", faceCache.size());
                    }
                    return id;
                } finally {
                    lock.writeLock().unlock();
                }
            } else {
                logger.warn("缓存数据已满，不能再注册人脸了！ {}", faceCache.size());
            }
        }
        logger.warn("注册的faceModel对象数据不规范！ {}", faceModel);
        return null;
    }

    @Override
    public List<FaceModelScore> search(float[] features, int topN, float minimum_score) {
        List<FaceModelScore> list = new ArrayList<>();
        if (minimum_score <= 0.0F) {
            if (faceRegisterConfig.getScore() != null && faceRegisterConfig.getScore() > 0.0F) {
                minimum_score = faceRegisterConfig.getScore();
            } else {
                minimum_score = 0.68F;
            }
        }
        lock.readLock().lock();
        try {
            Iterator<Map.Entry<String, FaceModel>> iterator = faceCache.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, FaceModel> next = iterator.next();
                FaceModel faceModel = next.getValue();
                float score = cosineSimilarity(features, faceModel.getFeatures());
                if (score >= minimum_score) {
                    FaceModelScore faceModelScore = new FaceModelScore();
                    BeanUtils.copyProperties(faceModel, faceModelScore);
                    faceModelScore.setScore(score);
                    list.add(faceModelScore);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        List<FaceModelScore> collect = list.stream()
                .sorted(Comparator.comparing(FaceModelScore::getScore).reversed())
                .collect(Collectors.toList());

        if (collect.size() > topN) {
            return collect.subList(0, topN);
        }
        return collect;
    }

    @Override
    public List<FaceModelScore> search(float[] features) {
        int topN;
        float minimum_score;
        if (faceRegisterConfig.getTopN() == null || faceRegisterConfig.getTopN() <= 0) {
            topN = 20;
        } else {
            topN = faceRegisterConfig.getTopN();
        }
        if (faceRegisterConfig.getScore() == null || faceRegisterConfig.getScore() <= 0) {
            minimum_score = 0.68F;
        } else {
            minimum_score = faceRegisterConfig.getScore();
        }
        return search(features, topN, minimum_score);
    }

    public float cosineSimilarity(float[] leftVector, float[] rightVector) {
        double dotProduct = 0.0;

        for (int i = 0; i < leftVector.length; ++i) {
            dotProduct += leftVector[i] * rightVector[i];
        }

        double d1 = 0.0;
        float[] var7 = leftVector;
        int var8 = leftVector.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            float value = var7[var9];
            d1 += Math.pow(value, 2.0);
        }

        double d2 = 0.0;
        float[] var16 = rightVector;
        int var13 = rightVector.length;

        for (int var11 = 0; var11 < var13; ++var11) {
            float value = var16[var11];
            d2 += Math.pow(value, 2.0);
        }

        double cosineSimilarity;
        if (!(d1 <= 0.0) && !(d2 <= 0.0)) {
            cosineSimilarity = dotProduct / (Math.sqrt(d1) * Math.sqrt(d2));
        } else {
            cosineSimilarity = 0.0;
        }

        return (float) cosineSimilarity;
    }
}
