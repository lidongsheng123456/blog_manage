package com.zjjhy.common.annotation;


import com.zjjhy.common.enums.OperationTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义注解,用于标识某个方法需要进行功能字段自动填充处理
 */
@Target(ElementType.METHOD)//（元注解）决定当前注解作用于哪些元素上面
@Retention(RetentionPolicy.RUNTIME)//（元注解）代表当前自定义注解的生命周期
@Documented
public @interface AutoFill {
    //数据库操作类型,UPDATE,INSERT
    OperationTypeEnum value();
}
