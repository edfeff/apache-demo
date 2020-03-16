package com.wpp.shiro.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public interface PermissionDao {
    JSONObject getUserPermission(String username);

    Set<String> getAllMenu();

    Set<String> getAllPermission();
}
