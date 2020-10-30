package com.cxy.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author cxy
 * @Date 2020/10/30 13:54
 * @Version 1.0
 */
@Data
public class Amenddto implements Serializable {

    private String username;

    private String newUsername;

    private String email;
}
