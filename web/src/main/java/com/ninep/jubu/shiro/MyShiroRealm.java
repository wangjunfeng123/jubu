package com.ninep.jubu.shiro;

import com.ninep.jubu.cache.JubuThreadCache;
import com.ninep.jubu.domain.Role;
import com.ninep.jubu.domain.User;
import com.ninep.jubu.enums.UserWorkStatus;
import com.ninep.jubu.service.UserRoleService;
import com.ninep.jubu.service.UserService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 自定义的realm.
 * @since 2018/07/02
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户的用户名
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        //根据用户名查询用户
        User user = userService.getUserByName(loginName);
        if (user != null) {//用户存在
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            Set<String> set = new HashSet<>();
            List<Integer> roleIds = userRoleService.getRoleIds(user.getUserId());
            roleIds.stream().forEach(roleId ->{
//                set.add(roleId);
            });
            //此处add进去的到底是什么

            //todo
            //获取用户对应的权限
            info.setRoles(set);
            List<Role> roleList = user.getRoleList();
            //把该用户的权限放到info中
            for (Role role : roleList) {
//                info.addStringPermissions(role.getPermissionsName());
            }
            return info;
        }
        return null;
    }

    /**
     * 认证、登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("MyShiroRealm  doGetAuthenticationInfo token:{}", ToStringBuilder.reflectionToString(token));
        logger.info(token.getCredentials().toString());

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userService.getUserByName(usernamePasswordToken.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (user != null && user.getStatus().equals(UserWorkStatus.NOT_VERIFY.getIndex())) {
            throw new UnknownAccountException();
        }
        if (user != null && user.getStatus().equals(UserWorkStatus.LOCKED_WORK.getIndex())) {
            throw new LockedAccountException();
        }
        //正常用户验证
        //密码加密方式salt自定义，hash次数1024，Base64解码，加密算法为md5
        JubuThreadCache.setUser(user);
        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
                ByteSource.Util.bytes("farm"), getName());
    }
}