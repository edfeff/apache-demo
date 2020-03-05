package com.wpp.dubbocat;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApp.class, args);
        BootServiceInvoker bean = context.getBean(BootServiceInvoker.class);
        bean.hello();
    }

}
