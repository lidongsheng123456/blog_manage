package com.zjjhy.exception;

import com.zjjhy.common.enums.ResultCodeEnum;

public class SystemException extends RuntimeException {
    private String code;
    private String msg;

    public SystemException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SystemException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.code;
        this.msg = resultCodeEnum.msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
