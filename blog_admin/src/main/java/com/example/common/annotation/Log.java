package com.example.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，记录日志
 */
@Target(ElementType.METHOD) //(元注解)指定注解可以用于方法上
@Retention(RetentionPolicy.RUNTIME) //（元注解）指定注解在运行时保留
@Documented //（元注解）指定注解包含在 JavaDoc 中
//只需要单纯记录日志不需要成员属性来判断做具体操作
public @interface Log {
}
