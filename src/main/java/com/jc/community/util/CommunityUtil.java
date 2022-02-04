package com.jc.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    //DM5加密 只能加密不能解密
    //一个密码永远加密成一个东西
    //所以随机加一个字符串拼一起
    public static String MD5(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
