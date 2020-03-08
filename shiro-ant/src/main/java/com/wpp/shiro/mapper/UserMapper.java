package com.wpp.shiro.mapper;

import com.wpp.shiro.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangpp
 */
public interface UserMapper {
    User findByUsername(@Param( "username" ) String username);
}
