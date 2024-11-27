package com.zjjhy.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.exception.BusinessException;
import com.zjjhy.mapper.OperateLogMapper;
import com.zjjhy.pojo.entity.OperateLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        /**
         * @Autowired 注入 HttpServletRequest：在大多数情况下可以工作，但在多线程环境或某些特定的 AOP 切面中可能无法获取到当前请求的上下文。
         * 使用 RequestContextHolder：这是一种更安全的方法，确保在任何情况下都能获取到当前请求的 HttpServletRequest。
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        String token = null;
        if (request != null) {
            token = request.getHeader("token");
        }
        //获取token令牌并破译其中的载荷并用-切割拿到第一个参数id第二个参数是用户角色
        String userId = JWT.decode(token).getAudience().get(0).split("-")[0];
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
