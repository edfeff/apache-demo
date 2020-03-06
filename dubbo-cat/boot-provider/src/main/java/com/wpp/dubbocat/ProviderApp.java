package com.wpp.dubbocat;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.wpp.stater.annotation.EnableWppAutoConfiguration;
import com.wpp.stater.bean.Wpp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 自动配置
 */
@SpringBootApplication
@EnableDubboConfiguration
//@EnableWppAutoConfiguration
public class ProviderApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProviderApp.class, args);
        Wpp bean = context.getBean(Wpp.class);
        System.out.println(bean);
    }

}
