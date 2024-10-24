package com.lyc.exception;


public class Seetaface6Exception extends Exception {
    // 默认构造函数
    public Seetaface6Exception() {
        super();
    }

    // 带消息的构造函数
    public Seetaface6Exception(String message) {
        super(message);
    }

    // 带消息和原因的构造函数
    public Seetaface6Exception(String message, Throwable cause) {
        super(message, cause);
    }

    // 只带原因的构造函数
    public Seetaface6Exception(Throwable cause) {
        super(cause);
    }
}