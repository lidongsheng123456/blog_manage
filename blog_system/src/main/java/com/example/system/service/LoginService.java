package com.example.system.service;

import jakarta.servlet.http.HttpSession;
import com.example.system.domain.User;
import com.example.system.domain.dto.UserDto;

public interface LoginService {
    /**
     * 登录
     *
     * @param UserDto
     * @return
     */
    User login(UserDto UserDto, HttpSession session);

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
