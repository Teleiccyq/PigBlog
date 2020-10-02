package com.cxy.service.impl;

import com.cxy.entity.Blog;
import com.cxy.mapper.BlogMapper;
import com.cxy.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
