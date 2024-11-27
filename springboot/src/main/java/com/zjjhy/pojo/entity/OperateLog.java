package com.zjjhy.pojo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class OperateLog {
    private Long id;
    private String operateUserId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long costTime;

    public OperateLog() {
    }

    public OperateLog(Long id, String operateUserId, LocalDateTime operateTime, String className, String methodName, String methodParams, String returnValue, Long costTime) {
        this.id = id;
        this.operateUserId = operateUserId;
        this.operateTime = operateTime;
        this.className = className;
        this.methodName = methodName;
        this.methodParams = methodParams;
        this.returnValue = returnValue;
        this.costTime = costTime;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return operateUserId
     */
    public String getOperateUserId() {
        return operateUserId;
    }

    /**
     * 设置
     * @param operateUserId
     */
    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    /**
     * 获取
     * @return operateTime
     */
    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    /**
     * 设置
     * @param operateTime
     */
    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取
     * @return methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取
     * @return methodParams
     */
    public String getMethodParams() {
        return methodParams;
    }

    /**
     * 设置
     * @param methodParams
     */
    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    /**
     * 获取
     * @return returnValue
     */
    public String getReturnValue() {
        return returnValue;
    }

    /**
     * 设置
     * @param returnValue
     */
    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    /**
     * 获取
     * @return costTime
     */
    public Long getCostTime() {
        return costTime;
    }

    /**
     * 设置
     * @param costTime
     */
    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String toString() {
        return "OperateLog{id = " + id + ", operateUserId = " + operateUserId + ", operateTime = " + operateTime + ", className = " + className + ", methodName = " + methodName + ", methodParams = " + methodParams + ", returnValue = " + returnValue + ", costTime = " + costTime + "}" ;
    }
}
