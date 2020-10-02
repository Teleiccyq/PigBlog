package com.cxy.utils;

import com.cxy.shiro.AcctountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {

    public static AcctountProfile getProfile(){
        return (AcctountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
