package com.zjjhy.service;

import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;

public interface LoginService {
    User login(UserDto UserDto);

    int register(UserDto userDto);

    public abstract User selectById(Integer id);
}
