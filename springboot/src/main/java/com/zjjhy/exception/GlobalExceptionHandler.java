package com.zjjhy.exception;

import com.zjjhy.common.Result;
import com.zjjhy.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException se) {
        log.info("系统异常:{}", se.getMsg());
        return Result.error(se.getCode(), se.getMsg());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException be) {
        log.info("业务异常:{}", be.getMsg());
        return Result.error(be.getCode(), be.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        log.info("异常:", ex);
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }
}
