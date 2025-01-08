package com.example.common.exception;

import com.example.common.enums.ResultCodeEnum;
import com.example.common.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException se) {
        log.error("系统异常:code={},msg={}", se.getCode(), se.getMessage(), se.getCause());
        return Result.error(se.getCode(), se.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException be) {
        log.error("业务异常:code={},msg={}", be.getCode(), be.getMessage(), be.getCause());
        return Result.error(be.getCode(), be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        // getCause 方法是 Throwable 类中的一个方法，用于获取当前异常的原因
        log.error("异常:msg={}", ex.getMessage(), ex);
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }
}
