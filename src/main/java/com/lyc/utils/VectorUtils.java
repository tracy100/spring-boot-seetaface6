package com.lyc.utils;

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

    public static void main(String[] args) {
        double[] vector = {1.0, 2.0, 3.0};
        double[] normalizedVector = normalize(vector);

        // 输出归一化后的向量
        for (double v : normalizedVector) {
            System.out.println(v);
        }
    }
}