package com.wpp.shiro.util.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

public class One2Many extends JSONObject {
    //角色集合
    private Set<String> roleList;
    //菜单集合
    private Set<String> menuList;
    //权限集合
    private Set<String> permissionList;
    //权限集合
    private Set<Integer> permissionIds;
    //
    private List<JSONObject> picList;
    //
    private List<JSONObject> menus;
    //
    private List<JSONObject> users;
    //
    private List<JSONObject> permissions;
}
