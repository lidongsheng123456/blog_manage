package com.example.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DocsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer accountId;
    private String docsTitle;
    private String docsContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    public DocsDto() {
    }

    public DocsDto(Integer id, Integer accountId, String docsTitle, String docsContent, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.accountId = accountId;
        this.docsTitle = docsTitle;
        this.docsContent = docsContent;
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
     * @return accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 设置
     *
     * @param accountId
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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
        return "DocsDto{id = " + id + ", accountId = " + accountId + ", docsTitle = " + docsTitle + ", docsContent = " + docsContent + ", createTime = " + createTime + ", updateTime = " + updateTime + "}";
    }
}
