package com.zjjhy.common.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.zjjhy.common.context.BaseContext;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.mapper.OperateLogMapper;
import com.zjjhy.common.pojo.entity.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 记录增删改日志，注意不要给文件上传加，因为没有用axios设置统一请求格式，没有token
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知可以手动书写原始方法的调用，并拿到返回值
     * 环绕通知依赖形参ProceedingJoinPoint才能实现对原始方法的调用
     * 环绕通知可以隔离原始方法的调用执行
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.zjjhy.common.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //每次请求拦截器都会验证jwt令牌，并把令牌中的用户id，也就是创建jwt时使用的载荷（id）拿出来放到本地线程变量副本中，因此不用担心id是否是全局的唯一的,因为每次请求都会重新获取用户id
        String userId = String.valueOf(BaseContext.getCurrent());

        if (ObjectUtil.isEmpty(userId)) {
            throw new BusinessException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }
        //记录方法执行前的年月日时分秒
        LocalDateTime operateTime = LocalDateTime.now();

        //获取操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //获取操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录方法开始前的时间戳
        Long begin = System.currentTimeMillis();

        //开始执行切入点方法并拿到切入点方法的返回值
        Object resultValue = joinPoint.proceed();

        //记录方法开始后的时间戳
        Long end = System.currentTimeMillis();

        //记录方法返回值
        String returnValue = JSONObject.toJSONString(resultValue);

        //记录方法执行总耗时
        Long costTime = end - begin;

        OperateLog operateLog = new OperateLog(null, userId, operateTime, className, methodName, methodParams, returnValue, costTime);

        try {
            operateLogMapper.insertLog(operateLog);
            log.info("操作日志记录成功，日志信息：{}", operateLog);
        } catch (MyBatisSystemException e) {
            throw new BusinessException(ResultCodeEnum.LOG_ERROR);
        }

        //必须return，这里的返回值是用的原方法的返回值，不return的话就给前端响应了个空响应体
        return resultValue;
    }
}
