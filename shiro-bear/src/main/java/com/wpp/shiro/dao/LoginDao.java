package com.wpp.shiro.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {
    JSONObject getUser(@Param("username") String username, @Param("password") String password);
}
