package com.wpp.stater.spring.boot;

import com.wpp.stater.annotation.EnableWppAutoConfiguration;
import com.wpp.stater.bean.Wpp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(value = WppProperties.class)
@ConditionalOnBean(annotation = EnableWppAutoConfiguration.class)
public class WppAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Wpp getWpp(WppProperties properties) {
        Wpp wpp = new Wpp();
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("name", properties.getNameConfig().getName());
        nameMap.put("owner", properties.getNameConfig().getOwner());
        nameMap.put("version", properties.getNameConfig().getVersion());
        wpp.setNameMap(nameMap);


        Map<String, String> ageMap = new HashMap<>();
        ageMap.put("name", properties.getAgeConfig().getName());
        ageMap.put("owner", properties.getAgeConfig().getOwner());
        ageMap.put("version", properties.getAgeConfig().getVersion());
        wpp.setAgeMap(ageMap);

        return wpp;
    }

}
