package com.cxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.cxy.mapper")
@ComponentScan(basePackages = {"com.cxy.shiro","com.cxy.utils","com.cxy.service","com.cxy.config","com.cxy.common","com.cxy.controller"})
public class PigblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PigblogApplication.class, args);
    }

}
