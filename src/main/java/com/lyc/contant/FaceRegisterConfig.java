package com.lyc.contant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "seetaface6.registration")
public class FaceRegisterConfig {

    private String method;

    private String file_path;

    private Integer max_face;

    private Integer topN;

    private Float score;



    public Integer getTopN() {
        return topN;
    }

    public void setTopN(Integer topN) {
        this.topN = topN;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getMax_face() {
        return max_face;
    }

    public void setMax_face(Integer max_face) {
        this.max_face = max_face;
    }
}
