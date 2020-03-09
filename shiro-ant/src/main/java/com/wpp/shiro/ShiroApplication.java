package com.wpp.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author wangpp
 */
@SpringBootApplication
@MapperScan( basePackages = {"com.wpp.shiro.mapper"} )
public class ShiroApplication {
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("pages/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
