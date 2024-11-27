package com.zjjhy.pojo.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class DocsVo {
    private Integer id;
    private String username;
    private String docsTitle;
    private String docsContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public DocsVo() {
    }

    public DocsVo(Integer id, String username, String docsTitle, String docsContent, LocalDateTime createTime) {
        this.id = id;
        this.username = username;
        this.docsTitle = docsTitle;
        this.docsContent = docsContent;
        this.createTime = createTime;
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
     * @return docsTitle
     */
    public String getDocsTitle() {
        return docsTitle;
    }

    /**
     * 设置
     *
     * @param docsTitle
     */
    public void setDocsTitle(String docsTitle) {
        this.docsTitle = docsTitle;
    }

    /**
     * 获取
     *
     * @return docsContent
     */
    public String getDocsContent() {
        return docsContent;
    }

    /**
     * 设置
     *
     * @param docsContent
     */
    public void setDocsContent(String docsContent) {
        this.docsContent = docsContent;
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

    public String toString() {
        return "DocsVo{id = " + id + ", username = " + username + ", docsTitle = " + docsTitle + ", docsContent = " + docsContent + ", createTime = " + createTime + "}";
    }
}
