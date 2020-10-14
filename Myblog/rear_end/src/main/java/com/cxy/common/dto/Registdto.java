package com.cxy.common.dto;

import lombok.Data;
import sun.management.counter.perf.PerfInstrumentation;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class Registdto {

    @NotBlank(message = "呢称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    private String avatar;

    private Integer status;

    private LocalDateTime created;
}
