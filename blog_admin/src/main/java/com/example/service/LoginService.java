package com.example.service;

import com.example.common.pojo.dto.UserDto;
import com.example.common.pojo.entity.User;

public interface LoginService {
    /**
     * 登录
     *
     * @param UserDto
     * @return
     */
    User login(UserDto UserDto);

    /**
     * 注册
     *
     * @param userDto
     */
    void register(UserDto userDto);

    /**
     * 根据用户名查询
     *
     * @param id
     * @return
     */
    User selectById(Integer id);
}
