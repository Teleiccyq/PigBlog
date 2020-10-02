package com.cxy.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;   //200是正常，非200表示异常

    private String mesg;

    private Object data;

    //成功标识
    public static Result success(int code,String mesg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMesg(mesg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data){
        return success(200,"操作成功",data);
    }

    //失败标识
    public static Result fail(int code,String mesg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMesg(mesg);
        result.setData(data);
        return result;
    }

    public static Result fail(String mesg,Object data){
        return fail(400,mesg,data);
    }

    public static Result fail(String mesg){
        return fail(400,mesg,null);
    }

}
