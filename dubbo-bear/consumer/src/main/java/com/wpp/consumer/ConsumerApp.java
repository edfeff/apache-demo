package com.wpp.consumer;

import com.wpp.api.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author wangpp
 */
public class ConsumerApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext
                context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
        context.start();

        HelloService helloService = (HelloService) context.getBean("helloService");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.next();
            String response = helloService.hello(msg);
            System.out.println(response);
        }
    }

}
