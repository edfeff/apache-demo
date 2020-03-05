package com.wpp.dubbocat;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wpp.api.BootService;
import org.springframework.stereotype.Component;

/**
 * @author wangpp
 */
@Component
public class BootServiceInvoker {
    //    @Reference( url = "dubbo://localhost:20880" )
    @Reference()
    private BootService bootService;

    public void hello() {
        System.out.println(bootService.hello("boot consumer"));
    }
}
