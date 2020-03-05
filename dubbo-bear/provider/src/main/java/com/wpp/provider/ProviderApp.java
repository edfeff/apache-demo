package com.wpp.provider;

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

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
