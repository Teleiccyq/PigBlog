package com.cxy.service.impl;

import com.cxy.entity.User;
import com.cxy.mapper.UserMapper;
import com.cxy.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
