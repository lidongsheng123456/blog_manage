package com.zjjhy.common.aspect;

import com.auth0.jwt.JWT;
import com.zjjhy.common.Constants;
import com.zjjhy.common.annotation.AutoFill;
import com.zjjhy.common.enums.OperationType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面,实现公共字段自动填充处理逻辑
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.zjjhy.mapper.*.*(..)) && @annotation(com.zjjhy.common.annotation.AutoFill)")
    public void autoFillPointCut() {}

    /**
     * 前置通知,在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段的填充...");

        //获取httpServlet请求上下文
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获得数据库操作类型

        //获取到当前被拦截的方法的参数 -- 实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }

        Object pojo = args[0];
        log.info("args:{}", args);

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();

        String token = null;
        if (request != null) {
            token = request.getHeader("token");
        }

        String currentId = JWT.decode(token).getAudience().get(0).split("-")[0];

        //根据当前不同的操作类型,为对应的属性赋值通过反射赋值
        if (operationType == OperationType.INSERT) {
            //为四个公共字段赋值
            try {
                Method setCreateTime = pojo.getClass().getDeclaredMethod(Constants.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = pojo.getClass().getDeclaredMethod(Constants.SET_CREATE_USER, Long.class);
                Method setUpdateTime = pojo.getClass().getDeclaredMethod(Constants.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = pojo.getClass().getDeclaredMethod(Constants.SET_UPDATE_USER, Long.class);

                //通过反射为对象属性赋值
                setCreateTime.invoke(pojo, now);
                setCreateUser.invoke(pojo, currentId);
                setUpdateTime.invoke(pojo, now);
                setUpdateUser.invoke(pojo, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (operationType == OperationType.UPDATE) {
            //为两个公共字段赋值
            try {
                Method setUpdateTime = pojo.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = pojo.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                //通过反射为对象属性赋值
                setUpdateTime.invoke(pojo, now);
                setUpdateUser.invoke(pojo, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
