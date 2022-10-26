package com.lyc.config;

import com.lyc.contant.FaceRegisterConfig;
import com.lyc.register.CacheRegisterFace;
import com.lyc.register.RegisterFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaceSearchConfig {

    @Autowired
    FaceRegisterConfig faceRegisterConfig;

    @Bean
    public RegisterFace registerFace() {

        return new CacheRegisterFace(faceRegisterConfig);
    }

}
