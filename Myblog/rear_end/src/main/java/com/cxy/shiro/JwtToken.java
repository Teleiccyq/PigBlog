package com.cxy.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 将jwtToken传到自定义的Realm中
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
