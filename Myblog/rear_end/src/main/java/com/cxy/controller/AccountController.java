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
        boolean flag = registGetOne(registdto.getUsername());
        if (flag == false && userService.save(user)) {
            return Result.success(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("email", user.getEmail())
                    .map()
            );
        } else {
            return Result.fail("注册失败，用户名已存在");
        }
    }

    //用户注册判断是否存在用户名
    @GetMapping("/registgetone")
    public boolean registGetOne(@Validated String username) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username).last("LIMIT 1"));
        if (user != null) {
            //已存在该用户名
            return true;
        }
        return false;
    }

    //通过用户名查询
    @GetMapping("/getone")
    public Result getOne(@Validated String username, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user != null) {
            return Result.success(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("avatar", user.getAvatar())
                    .put("email", user.getEmail())
                    .map()
            );
        }
        return Result.fail("未查询到用户名为" + username + "的用户");

    }


    //通过ID查询
    @GetMapping("/getbyid")
    public User getById(@Validated Integer id, HttpServletResponse response) {
        User byId = userService.getById(id);
        return byId;
    }


    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success("操作成功");
    }
}
