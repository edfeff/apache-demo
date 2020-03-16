package com.wpp.shiro.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int countUser(JSONObject jsonObject);

    List<JSONObject> listUser(JSONObject jsonObject);

    List<JSONObject> getAllRoles();

    int queryExistUsername(JSONObject jsonObject);

    int addUser(JSONObject jsonObject);

    int updateUser(JSONObject jsonObject);

    List<JSONObject> listRole();

    List<JSONObject> listAllPermission();

    int inserRole(JSONObject jsonObject);

    int insertRolePermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    int removeOldPermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    int updateRoleName(JSONObject jsonObject);

    JSONObject getRoleAllInfo(JSONObject jsonObject);

    int removeRole(JSONObject jsonObject);

    int removeRoleAllPermission(JSONObject jsonObject);
}
