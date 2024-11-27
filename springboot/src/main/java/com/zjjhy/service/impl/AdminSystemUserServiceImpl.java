package com.zjjhy.service.impl;

import com.zjjhy.common.Constants;
import com.zjjhy.common.annotation.Log;
import com.zjjhy.mapper.AdminSystemUserMapper;
import com.zjjhy.pojo.dto.PwdDto;
import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;
import com.zjjhy.pojo.vo.PageVo;
import com.zjjhy.pojo.vo.PwdVo;
import com.zjjhy.pojo.vo.UserVo;
import com.zjjhy.service.AdminSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminSystemUserServiceImpl implements AdminSystemUserService {
    @Autowired
    private AdminSystemUserMapper adminSystemUserMapper;

    @Override
    public PageVo queryUserData(User user, Integer page, Integer pageSize) {
        PageVo<UserVo> pageVo = new PageVo<>();
        pageVo.setTotal(adminSystemUserMapper.queryUserTotal(user));
        pageVo.setData(adminSystemUserMapper.queryUserData(user, (page - 1) * pageSize, pageSize));
        return pageVo;
    }

    @Override
    public int addUser(UserDto userDto) {
        userDto.setCreateTime(LocalDateTime.now());
        userDto.setUpdateTime(LocalDateTime.now());
        userDto.setPwd(Constants.USER_DEFAULT_PASSWORD);
        return adminSystemUserMapper.addUser(userDto);
    }

    @Override
    public int deleteUser(List<Integer> ids) {
        return adminSystemUserMapper.deleteUser(ids);
    }

    @Override
    public int updateUser(UserDto userDto) {
        userDto.setUpdateTime(LocalDateTime.now());
        return adminSystemUserMapper.updateUser(userDto);
    }

    @Override
    public int personCenter(UserDto userDto) {
        userDto.setUpdateTime(LocalDateTime.now());
        return adminSystemUserMapper.personCenter(userDto);
    }

    @Override
    public PwdVo getPersonPwd(Integer id) {
        return adminSystemUserMapper.getPersonPwd(id);
    }

    @Override
    public int updatePwd(PwdDto pwdDto) {
        pwdDto.setUpdateTime(LocalDateTime.now());
        return adminSystemUserMapper.updatePwd(pwdDto);
    }

    @Override
    public List<UserVo> validateUser(UserDto userDto) {
        return adminSystemUserMapper.queryByUsername(userDto);
    }
}
