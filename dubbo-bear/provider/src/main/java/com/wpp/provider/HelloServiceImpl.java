package com.wpp.provider;

import com.wpp.api.HelloService;

/**
 * @author wangpp
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        return "provider:" + msg;
    }
}
