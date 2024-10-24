package com.lyc.utils;

import com.lyc.entities.FaceInfo;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;

/**
 * 人脸信息转换器
 * 人脸图片经过第一次识别后，可根把中间的定位信息存储到FaceInfo对象，当业务代码中需要使用的时候，可以直接用pgsql的原始对象做事情，不需要再次用识别器定位
 */
public class FaceInfoConvert {


    /**
     * 人脸信息 转 人脸在图片中的定位信息
     *
     * @param faceInfo 人脸信息
     * @return SeetaRect 人脸定位对象
     */
    public static SeetaRect toSeetaRect(FaceInfo faceInfo) {
        if (faceInfo == null || faceInfo.getX() == null || faceInfo.getY() == null
                || faceInfo.getHeight() == null || faceInfo.getWidth() == null) return null;
        SeetaRect rect = new SeetaRect();
        rect.x = faceInfo.getX();
        rect.y = faceInfo.getY();
        rect.height = faceInfo.getHeight();
        rect.width = faceInfo.getWidth();
        return rect;
    }

    public static SeetaPointF[] toSeetaPointF5Array(FaceInfo faceInfo) {
        if (faceInfo == null || faceInfo.getPoints() == null || faceInfo.getPoints().length != 5) return null;

        SeetaPointF[] points = new SeetaPointF[5];
        for (int i = 0; i < faceInfo.getPoints().length; i++) {
            double[] point = faceInfo.getPoints()[i];
            SeetaPointF pointF = new SeetaPointF();
            pointF.x = point[0];
            pointF.y = point[1];
            points[i] = pointF;
        }
        return points;
    }



}
