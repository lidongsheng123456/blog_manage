package com.zjjhy.common.pojo.entity;

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
    private static final long serialVersionUID = -46419863496522204L;

    private Integer id;
    /**
     * 操作人id
     */
    private String operateUserId;
    /**
     * 开始操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    /**
     * 获取
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return operateUserId
     */
    public String getOperateUserId() {
        return operateUserId;
    }

    /**
     * 设置
     *
     * @param operateUserId
     */
    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    /**
     * 获取
     *
     * @return operateTime
     */
    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    /**
     * 设置
     *
     * @param operateTime
     */
    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取
     *
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置
     *
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取
     *
     * @return methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置
     *
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取
     *
     * @return methodParams
     */
    public String getMethodParams() {
        return methodParams;
    }

    /**
     * 设置
     *
     * @param methodParams
     */
    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    /**
     * 获取
     *
     * @return returnValue
     */
    public String getReturnValue() {
        return returnValue;
    }

    /**
     * 设置
     *
     * @param returnValue
     */
    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    /**
     * 获取
     *
     * @return costTime
     */
    public Long getCostTime() {
        return costTime;
    }

    /**
     * 设置
     *
     * @param costTime
     */
    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String toString() {
        return "OperateLog{id = " + id + ", operateUserId = " + operateUserId + ", operateTime = " + operateTime + ", className = " + className + ", methodName = " + methodName + ", methodParams = " + methodParams + ", returnValue = " + returnValue + ", costTime = " + costTime + "}";
    }
}

