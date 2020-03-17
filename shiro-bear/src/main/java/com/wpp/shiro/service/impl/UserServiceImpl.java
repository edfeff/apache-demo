package com.wpp.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.dao.UserDao;
import com.wpp.shiro.service.UserService;
import com.wpp.shiro.util.CommonUtil;
import com.wpp.shiro.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public JSONObject listUser(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.countUser(jsonObject);
        List<JSONObject> list = userDao.listUser(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject getAllRoles() {
        List<JSONObject> roles = userDao.getAllRoles();
        return CommonUtil.successPage(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addUser(JSONObject jsonObject) {
        int exist = userDao.queryExistUsername(jsonObject);
        if (exist > 0) {
            return CommonUtil.errJson(ErrorEnum.E_10009);
        }
        userDao.addUser(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateUser(JSONObject jsonObject) {
        userDao.updateUser(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listRole() {
        List<JSONObject> roles = userDao.listRole();
        return CommonUtil.successPage(roles);
    }

    @Override
    public JSONObject listAllPermission() {
        List<JSONObject> permissions = userDao.listAllPermission();
        return CommonUtil.successPage(permissions);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addRole(JSONObject jsonObject) {
        userDao.inserRole(jsonObject);
        userDao.insertRolePermission(jsonObject.getString("roleId"), (List<Integer>) jsonObject.get("permissions"));
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateRole(JSONObject jsonObject) {
        String roleId = jsonObject.getString("roleId");
        List<Integer> newPerms = (List<Integer>) jsonObject.get("permissions");

        JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);

        Set<Integer> oldPerms = (Set<Integer>) roleInfo.get("permissionIds");

        dealRoleName(jsonObject, roleInfo);

        saveNewPermission(roleId, newPerms, oldPerms);

        removeOldPermission(roleId, newPerms, oldPerms);

        return CommonUtil.successJson();
    }


    private void saveNewPermission(String roleId, List<Integer> newPerms, Set<Integer> oldPerms) {
        ArrayList<Integer> waitInsert = new ArrayList<>();
        for (Integer newPerm : newPerms) {
            if (!oldPerms.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        }
        if (!waitInsert.isEmpty()) {
            userDao.insertRolePermission(roleId, waitInsert);
        }
    }

    private void removeOldPermission(String roleId, List<Integer> newPerms, Set<Integer> oldPerms) {
        List<Integer> waitRemove = new ArrayList<>();
        for (Integer oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        }
        if (!waitRemove.isEmpty()) {
            userDao.removeOldPermission(roleId, waitRemove);
        }
    }

    private void dealRoleName(JSONObject paramJson, JSONObject roleInfo) {
        String roleName = paramJson.getString("roleName");
        if (!roleName.equals(roleInfo.getString("roleName"))) {
            userDao.updateRoleName(paramJson);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject deleteRole(JSONObject jsonObject) {
        JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);
//        roleInfo.get("")
        return null;
    }
}
