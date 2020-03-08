package com.wpp.shiro.service;

import com.wpp.shiro.model.User;

/**
 * @author wangpp
 */
public interface UserService {
    User findByUsername(String username);
}
