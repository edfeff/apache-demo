package com.wpp.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.dao.LoginDao;
import com.wpp.shiro.service.LoginService;
import com.wpp.shiro.service.PermissionService;
import com.wpp.shiro.util.CommonUtil;
import com.wpp.shiro.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;

    @Autowired
    PermissionService permissionService;

    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        JSONObject info = new JSONObject();

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            info.put("result", "success");
        } catch (AuthenticationException e) {
            info.put("result", "fail");
        }

        return CommonUtil.successJson(info);
    }

    @Override
    public JSONObject getUser(String username, String password) {
        return loginDao.getUser(username, password);
    }

    @Override
    public JSONObject getInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject info = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        return CommonUtil.successJson(info);
    }

    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonUtil.successJson();
    }
}
