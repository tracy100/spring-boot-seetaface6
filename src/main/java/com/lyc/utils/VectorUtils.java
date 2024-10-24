package com.lyc.utils;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

/**
 * 向量归一化处理，还有待测试
 */
public class VectorUtils {

    /**
     * 对向量进行归一化处理
     *
     * @param vector 输入的向量
     * @return 归一化后的向量
     */
    public static double[] normalize(double[] vector) {
        double norm = 0.0;

        // 计算向量的 L2 范数
        for (double v : vector) {
            norm += v * v;
        }
        norm = Math.sqrt(norm);

        // 如果向量的范数为 0，返回原向量
        if (norm == 0) {
            return vector;
        }

        // 归一化向量
        double[] normalizedVector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            normalizedVector[i] = vector[i] / norm;
        }

        return normalizedVector;
    }

    public static float cosineSimilarity(float[] leftVector, float[] rightVector) {
        if (leftVector.length != rightVector.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }

        // 将 float[] 转换为 double[]
        double[] leftVectorDouble = new double[leftVector.length];
        for (int i = 0; i < leftVector.length; i++) {
            leftVectorDouble[i] = leftVector[i];
        }

        double[] rightVectorDouble = new double[rightVector.length];
        for (int i = 0; i < rightVector.length; i++) {
            rightVectorDouble[i] = rightVector[i];
        }

        RealVector v1 = new ArrayRealVector(leftVectorDouble);
        RealVector v2 = new ArrayRealVector(rightVectorDouble);

        double dotProduct = v1.dotProduct(v2);
        double normV1 = v1.getNorm();
        double normV2 = v2.getNorm();

        if (normV1 == 0.0 || normV2 == 0.0) {
            return 0.0f;
        }

        double cosineSimilarity = dotProduct / (normV1 * normV2);
        return (float) cosineSimilarity;
    }


    public static void main(String[] args) {

    }
}