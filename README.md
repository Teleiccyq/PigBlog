# PigBlog
个人博客源码

## 前后端分离项目：

> 前端：Vue，element-ui，axios，基于Vue的markdown编辑器mavon-editor 

![image-20201014203052029](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201014203052029.png)

> **后端：Spring Boot，使用Mybatis puls整合，整合shiro+jwt，并会话共享**

## ![image-20201014203027272](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201014203027272.png)

## 跨域问题：

*前后端分离跨域问题就必不可免的，在后台进行全局跨域处理*

~~~java
package com.cxy.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 *
 */
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }
}

~~~

## 将Spring Boot项目在cmd中启动：

在项目中 Maven --> package

进入项目 目录打开cmd

执行 mvn install 命令(出现BUILD SUCCESS 证明打包成功)

打开target目录

在当前窗口继续输入命令：java -jar target/打包出来的jar全名



