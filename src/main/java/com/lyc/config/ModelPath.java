package com.lyc.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "seetaface6.model")
public class ModelPath {

    private String[] face_recognizer;
    private String[] face_recognizer_mask;
    private String[] face_recognizer_light;
    private String[] age_predictor;
    private String[] face_landmarker_pts5;
    private String[] face_landmarker_pts68;
    private String[] pose_estimation;
    private String[] eye_state;
    private String[] face_detector;
    private String[] face_landmarker_mask_pts5;
    private String[] mask_detector;
    private String[] gender_predictor;
    private String[] quality_lbn;
    private String fas_first;
    private String fas_second;

    public String[] getFace_recognizer() {
        return face_recognizer;
    }

    public void setFace_recognizer(String[] face_recognizer) {
        this.face_recognizer = face_recognizer;
    }

    public String[] getFace_recognizer_mask() {
        return face_recognizer_mask;
    }

    public void setFace_recognizer_mask(String[] face_recognizer_mask) {
        this.face_recognizer_mask = face_recognizer_mask;
    }

    public String[] getFace_recognizer_light() {
        return face_recognizer_light;
    }

    public void setFace_recognizer_light(String[] face_recognizer_light) {
        this.face_recognizer_light = face_recognizer_light;
    }

    public String[] getAge_predictor() {
        return age_predictor;
    }

    public void setAge_predictor(String[] age_predictor) {
        this.age_predictor = age_predictor;
    }

    public String[] getFace_landmarker_pts5() {
        return face_landmarker_pts5;
    }

    public void setFace_landmarker_pts5(String[] face_landmarker_pts5) {
        this.face_landmarker_pts5 = face_landmarker_pts5;
    }

    public String[] getFace_landmarker_pts68() {
        return face_landmarker_pts68;
    }

    public void setFace_landmarker_pts68(String[] face_landmarker_pts68) {
        this.face_landmarker_pts68 = face_landmarker_pts68;
    }

    public String[] getPose_estimation() {
        return pose_estimation;
    }

    public void setPose_estimation(String[] pose_estimation) {
        this.pose_estimation = pose_estimation;
    }

    public String[] getEye_state() {
        return eye_state;
    }

    public void setEye_state(String[] eye_state) {
        this.eye_state = eye_state;
    }

    public String[] getFace_detector() {
        return face_detector;
    }

    public void setFace_detector(String[] face_detector) {
        this.face_detector = face_detector;
    }

    public String[] getFace_landmarker_mask_pts5() {
        return face_landmarker_mask_pts5;
    }

    public void setFace_landmarker_mask_pts5(String[] face_landmarker_mask_pts5) {
        this.face_landmarker_mask_pts5 = face_landmarker_mask_pts5;
    }

    public String[] getMask_detector() {
        return mask_detector;
    }

    public void setMask_detector(String[] mask_detector) {
        this.mask_detector = mask_detector;
    }

    public String[] getGender_predictor() {
        return gender_predictor;
    }

    public void setGender_predictor(String[] gender_predictor) {
        this.gender_predictor = gender_predictor;
    }

    public String[] getQuality_lbn() {
        return quality_lbn;
    }

    public void setQuality_lbn(String[] quality_lbn) {
        this.quality_lbn = quality_lbn;
    }

    public String getFas_first() {
        return fas_first;
    }

    public void setFas_first(String fas_first) {
        this.fas_first = fas_first;
    }

    public String getFas_second() {
        return fas_second;
    }

    public void setFas_second(String fas_second) {
        this.fas_second = fas_second;
    }
}
