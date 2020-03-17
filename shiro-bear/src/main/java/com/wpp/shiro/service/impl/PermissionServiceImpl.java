package com.wpp.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.dao.PermissionDao;
import com.wpp.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public JSONObject getUserPermission(String username) {
        JSONObject userPermission = getUserPermissionFromDB(username);
        return userPermission;
    }

    private JSONObject getUserPermissionFromDB(String username) {

        JSONObject userPermission = permissionDao.getUserPermission(username);

        int adminRoleId = 1;

        String roleIdKey = "roleId";

        if (adminRoleId == userPermission.getIntValue(roleIdKey)) {
            Set<String> menus = permissionDao.getAllMenu();
            Set<String> permissions = permissionDao.getAllPermission();
            userPermission.put("menuList", menus);
            userPermission.put("permissionList", permissions);
        }

        return userPermission;
    }
}
