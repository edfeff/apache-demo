package com.wpp.provider;

import com.wpp.config.AddressConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author wangpp
 */
public class ProviderApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext
                context = new ClassPathXmlApplicationContext("applicationContext-provider.xml");
        context.start();
//        AddressConfig bean = context.getBean(AddressConfig.class);
//        System.out.println(bean);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
