package com.example.hugo.aopdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 第一个@Target表示这个注释只能给函数使用
 * 第二个@Retention 表示注解内容需要包含再Class字节码里，属于运行时需要的。
 * @interface 用于定义一个注释
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityCheckAnnotation {
    //declarePermssion是一个函数，其实代表了注解里的参数
    public String declaredPermission();
}
