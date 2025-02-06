package com.example.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (OperateLog)实体类
 *
 * @author makejava
 * @since 2024-12-07 14:15:45
 */
public class OperateLog implements Serializable {
    private Integer id;
    /**
     * 操作人id
     */
    private String operateUserId;
    /**
     * 开始操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operateTime;
    /**
     * 操作类名
     */
    private String className;
    /**
     * 操作的方法名
     */
    private String methodName;
    /**
     * 形参接收的参数
     */
    private String methodParams;
    /**
     * 响应的数据
     */
    private String returnValue;
    /**
     * 总耗时（毫秒）
     */
    private Long costTime;

    public OperateLog() {
    }

    public OperateLog(Integer id, String operateUserId, LocalDateTime operateTime, String className, String methodName, String methodParams, String returnValue, Long costTime) {
        this.id = id;
        this.operateUserId = operateUserId;
        this.operateTime = operateTime;
        this.className = className;
        this.methodName = methodName;
        this.methodParams = methodParams;
        this.returnValue = returnValue;
        this.costTime = costTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "id=" + id +
                ", operateUserId='" + operateUserId + '\'' +
                ", operateTime=" + operateTime +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodParams='" + methodParams + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", costTime=" + costTime +
                '}';
    }
}

