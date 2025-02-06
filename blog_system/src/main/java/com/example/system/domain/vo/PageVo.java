package com.example.system.domain.vo;

import java.io.Serializable;
import java.util.List;

public class PageVo<T> implements Serializable {
    private Integer total;
    private List<T> data;

    public PageVo() {
    }

    public PageVo(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    /**
     * 获取
     *
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置
     *
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取
     *
     * @return data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 设置
     *
     * @param data
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    public String toString() {
        return "PageVo{total = " + total + ", data = " + data + "}";
    }
}
