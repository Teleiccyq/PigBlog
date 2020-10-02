package com.cxy.controller;


import com.cxy.common.lang.Result;
import com.cxy.entity.User;
import com.cxy.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user = userService.getById(1L);
        return Result.success(user);
    }

    @GetMapping("/login")
    public Result test(){
        User user = userService.getById(2L);
        return Result.fail("没有该id的用户");
    }

    @GetMapping("/test")
    public String test1(){
        return "test";
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user);
    }
}
