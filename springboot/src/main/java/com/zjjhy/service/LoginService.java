package com.zjjhy.service;

import com.zjjhy.common.pojo.dto.UserDto;
import com.zjjhy.common.pojo.entity.User;

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
