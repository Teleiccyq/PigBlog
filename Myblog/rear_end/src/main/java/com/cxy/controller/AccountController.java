package com.cxy.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.common.dto.Logindto;
import com.cxy.common.dto.Registdto;
import com.cxy.common.lang.Result;
import com.cxy.entity.User;
import com.cxy.service.UserService;
import com.cxy.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    //登录
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody Logindto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        //用户密码加密----------------SecureUtil.md5()
        if (!user.getPassword().equals((SecureUtil.md5(loginDto.getPassword())))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    //注册
    @CrossOrigin
    @PostMapping("/regist")
    public Result reqist(@Validated @RequestBody Registdto registdto, HttpServletResponse response) {
        User user = new User();
        user.setUsername(registdto.getUsername());
        user.setPassword(SecureUtil.md5(registdto.getPassword()));
        user.setEmail(registdto.getEmail());
        user.setAvatar(registdto.getAvatar());
        user.setStatus(registdto.getStatus());
        user.setCreated(registdto.getCreated());

        if (userService.save(user)) {
            return Result.success(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("email", user.getEmail())
                    .put("avatar", user.getAvatar())
                    .map()
            );
        } else {
            return Result.fail("注册失败，用户名已存在");
        }
    }

    @CrossOrigin
    @GetMapping("/findone")
    public Result findOne(@Validated @RequestBody Logindto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .put("avatar", user.getAvatar())
                .map());
    }

    @CrossOrigin
    @GetMapping("/find")
    public List<User> find(@Validated @RequestBody Logindto loginDto, HttpServletResponse response){
        List<User> list = userService.list(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        return list;
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
