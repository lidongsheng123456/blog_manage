package com.zjjhy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.enums.RoleEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.common.interface_constants.Constants;
import com.zjjhy.common.util.TokenUtils;
import com.zjjhy.mapper.LoginMapper;
import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;
import com.zjjhy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    @Override
    public User login(UserDto userDto) {
        User user = loginMapper.selectByUsername(userDto.getUsername());
        //空返回true isEmpty会检查String list array 的长度是否为0 其他对象则检查是否为null
        if (ObjectUtil.isEmpty(user)) {
            throw new BusinessException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        if (!userDto.getPwd().equals(user.getPwd())) {
            throw new BusinessException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        //拼接token的载荷
        String tokenData = user.getId() + "-" + RoleEnum.ADMIN.getRole();
        String token = TokenUtils.createToken(tokenData, user.getPwd());
        user.setToken(token);

        return user;
    }

    /**
     * 注册
     *
     * @param userDto
     * @return
     */
    @Override
    public int register(UserDto userDto) {
        //1. 注册判断数据库是否存在当前用户名的数据
        User user = loginMapper.selectByUsername(userDto.getUsername());
        //2. 用户不为空则代表当前用户名重复，不能注册，抛出异常
        if (ObjectUtil.isNotNull(user)) {
            throw new BusinessException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        //3. 如果当前用户名不重复，则判断当前用户的密码是否为空，给个接口中的成员变量默认值
        if (ObjectUtil.isEmpty(userDto.getPwd())) {
            user.setPwd(Constants.USER_DEFAULT_PASSWORD);
        }
        //4. 判断前端传递的数据中有没有姓名，没有则把当前数据的用户名赋值给姓名
        if (ObjectUtil.isEmpty(userDto.getName())) {
            userDto.setName(userDto.getUsername());
        }

        userDto.setRole(RoleEnum.ADMIN.getRole());
        userDto.setCreateTime(LocalDateTime.now());
        userDto.setUpdateTime(LocalDateTime.now());
        return loginMapper.register(userDto);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User selectById(Integer id) {
        return loginMapper.selectById(id);
    }
}
