package com.wpp.stater.spring.boot;

import com.wpp.stater.config.AgeConfig;
import com.wpp.stater.config.NameConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.wpp")
public class WppProperties {
    private AgeConfig ageConfig;
    private NameConfig nameConfig;

    public AgeConfig getAgeConfig() {
        return ageConfig;
    }

    public void setAgeConfig(AgeConfig ageConfig) {
        this.ageConfig = ageConfig;
    }

    public NameConfig getNameConfig() {
        return nameConfig;
    }

    public void setNameConfig(NameConfig nameConfig) {
        this.nameConfig = nameConfig;
    }
}
