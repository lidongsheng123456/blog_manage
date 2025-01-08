package com.example.common.pojo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PwdDto implements Serializable {
    private Integer id;
    private String confirmPwd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    public PwdDto() {
    }

    public PwdDto(Integer id, String confirmPwd, LocalDateTime updateTime) {
        this.id = id;
        this.confirmPwd = confirmPwd;
        this.updateTime = updateTime;
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
     * @return confirmPwd
     */
    public String getConfirmPwd() {
        return confirmPwd;
    }

    /**
     * 设置
     *
     * @param confirmPwd
     */
    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    /**
     * 获取
     *
     * @return updateTime
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     *
     * @param updateTime
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String toString() {
        return "PwdDto{id = " + id + ", confirmPwd = " + confirmPwd + ", updateTime = " + updateTime + "}";
    }
}
