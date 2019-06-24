package com.wasu.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cs
 * @Date: 2019/6/13 10:22
 */
public class R extends HashMap <String,Object>{
    //构造函数
    public R() {
        put("code",0);
    }
    //未知异常返回
    public static R error(){
        return error(500,"未知异常，请联系管理员");
    }
    //已知异常返回
    public static  R error(String msg){
        return error(500,msg);
    }
    //成功返回
    public static R ok(){
        return new R();
    }
    //成功附加信息返回
    public static R ok(String msg){
        R r = new R();
        r.put("msg",msg);
        return r;
    }
    //成功附件多信息返回
    public  static R ok(Map<String,Object> map){
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error(Integer code, String msg) {
        R r = new R();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
