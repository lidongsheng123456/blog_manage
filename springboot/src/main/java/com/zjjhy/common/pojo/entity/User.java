package com.zjjhy.common.pojo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String pwd;
    private String name;
    private String imgUrl;
    private String role;
    private String phone;
    private String email;
    private String token;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    public User() {
    }

    public User(Integer id, String username, String pwd, String name, String imgUrl, String role, String phone, String email, String token, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.name = name;
        this.imgUrl = imgUrl;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.token = token;
        this.createTime = createTime;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     *
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置
     *
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取
     *
     * @return createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     *
     * @param createTime
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
        return "User{id = " + id + ", username = " + username + ", pwd = " + pwd + ", name = " + name + ", imgUrl = " + imgUrl + ", role = " + role + ", phone = " + phone + ", email = " + email + ", token = " + token + ", createTime = " + createTime + ", updateTime = " + updateTime + "}";
    }
}
