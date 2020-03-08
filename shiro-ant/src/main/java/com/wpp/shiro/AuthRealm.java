package com.wpp.shiro;

import com.wpp.shiro.model.Permission;
import com.wpp.shiro.model.Role;
import com.wpp.shiro.model.User;
import com.wpp.shiro.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wangpp
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        Set<Role> roleSet = user.getRoles();

        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role role : roleSet) {
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionList)) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);

        return info;
    }

    /**
     * 认证通过后，认证的相关信息放入到AuthRealm中
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);

        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

}
