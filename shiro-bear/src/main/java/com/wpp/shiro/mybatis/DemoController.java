package com.wpp.shiro.mybatis;

import com.wpp.shiro.mybatis.dao.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/te")
public class DemoController {
    @Autowired
    A a;

    @RequestMapping("/t1")
    public String t1() {
        return a.toString();
    }
}
