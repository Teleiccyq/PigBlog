package com.cxy.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AcctountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
