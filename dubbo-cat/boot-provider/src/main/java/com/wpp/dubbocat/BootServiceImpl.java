package com.wpp.dubbocat;

import com.alibaba.dubbo.config.annotation.Service;
import com.wpp.api.BootService;
import org.springframework.stereotype.Component;

/**
 * @author wangpp
 */
@Component
@Service( interfaceClass = BootService.class )
public class BootServiceImpl implements BootService {
    @Override
    public String hello(String msg) {
        return "boot-provider: " + msg;
    }

}
