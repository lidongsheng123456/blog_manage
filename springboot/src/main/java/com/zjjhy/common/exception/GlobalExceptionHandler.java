package com.zjjhy.common.exception;

import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException se) {
        //没必要 调用 getCause 异常原因就只有当前这个异常，没有其他异常原因
        log.info("系统异常:code={},msg={}", se.getCode(), se.getMessage());
        return Result.error(se.getCode(), se.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException be) {
        log.info("业务异常:code={},msg={}", be.getCode(), be.getMessage());
        return Result.error(be.getCode(), be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        // getCause 方法是 Throwable 类中的一个方法，用于获取当前异常的原因
        log.info("异常:msg={}", ex.getMessage(), ex.getCause());
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }
}
