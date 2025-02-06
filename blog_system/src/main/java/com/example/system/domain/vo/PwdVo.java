package com.example.system.domain.vo;

import java.io.Serializable;

public class PwdVo implements Serializable {
    private String pwd;

    public PwdVo() {
    }

    public PwdVo(String pwd) {
        this.pwd = pwd;
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

    public String toString() {
        return "PwdVo{pwd = " + pwd + "}";
    }
}
