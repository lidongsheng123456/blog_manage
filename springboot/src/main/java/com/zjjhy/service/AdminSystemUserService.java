package com.zjjhy.service;

import com.zjjhy.pojo.dto.PwdDto;
import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;
import com.zjjhy.pojo.vo.PageVo;
import com.zjjhy.pojo.vo.PwdVo;
import com.zjjhy.pojo.vo.UserVo;

import java.util.List;

public interface AdminSystemUserService {
    PageVo queryUserData(User user, Integer page, Integer pageSize);

    int addUser(UserDto userDto);

    int deleteUser(List<Integer> ids);

    int updateUser(UserDto userDto);

    int personCenter(UserDto userDto);

    PwdVo getPersonPwd(Integer id);

    int updatePwd(PwdDto pwdDto);

    List<UserVo> validateUser(UserDto userDto);
}
