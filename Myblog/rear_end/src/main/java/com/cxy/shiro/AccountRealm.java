package com.cxy.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.cxy.entity.User;
import com.cxy.service.UserService;
import com.cxy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 去数据库中进行匹配 返回用户信息给shiro
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    //判断token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    //获取权限，封装成info返回给shiro  授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("1");
//        Subject subject = SecurityUtils.getSubject();
//        User principal = (User) subject.getPrincipal();
//
//        info.addStringPermission(principal.getRestrict());
//
//        return info;
        return null;
    }


    //进行身份验证，获取token校验登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token;
        log.info("jwt----------------->{}", jwt);
        //获取userId
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
//        User user = userService.getById(Long.parseLong(userId));
        //查询用户
        User user = userService.getById(Long.valueOf(userId));
        //判断是否可用
        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }
        //将用户基本信息返回给Shiro
        AcctountProfile profile = new AcctountProfile();
        BeanUtil.copyProperties(user, profile);
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }

}
