package com.zjjhy.common.enums;

public enum RoleEnum {
    ADMIN("管理员"), USER("用户");

    private String role;

    RoleEnum() {
    }

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
