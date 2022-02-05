package com.jc.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // 这个注解写方法上 用来描述方法
@Retention(RetentionPolicy.RUNTIME)  // 有效时长 程序运行时有效
public @interface LoginRequired {
}
