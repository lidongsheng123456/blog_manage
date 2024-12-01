package com.zjjhy.service;

import com.zjjhy.common.pojo.dto.PwdDto;
import com.zjjhy.common.pojo.dto.UserDto;
import com.zjjhy.common.pojo.entity.User;
import com.zjjhy.common.pojo.vo.PageVo;
import com.zjjhy.common.pojo.vo.PwdVo;

import java.util.List;

public interface AdminSystemUserService {

    /**
     * 添加用户
     *
     * @param userDto
     */
    void addUser(UserDto userDto);

    /**
     * 删除用户
     *
     * @param ids
     */
    void deleteUser(List<Integer> ids);

    /**
     * 编辑用户
     *
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * 查询用户
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    PageVo queryUserData(User user, Integer page, Integer pageSize);

    /**
     * 修改个人信息
     *
     * @param userDto
     */
    void personCenter(UserDto userDto);

    /**
     * 查询用户密码
     *
     * @param id
     * @return
     */
    PwdVo getPersonPwd(Integer id);

    /**
     * 修改个人密码
     *
     * @param pwdDto
     */
    void updatePwd(PwdDto pwdDto);

    /**
     * 效验个人信息
     *
     * @param userDto
     */
    void validateUser(UserDto userDto);
}
