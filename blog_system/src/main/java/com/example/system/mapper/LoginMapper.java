package com.example.system.mapper;

import com.example.system.domain.dto.UserDto;
import com.example.system.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    UserDto login(UserDto userDto);

    /**
     * 注册
     *
     * @param userDto
     * @return
     */
    int register(UserDto userDto);

    /**
     * 按照用户名查询
     *
     * @param username
     * @return
     */
    @Select("select * from blog_account where username=#{username}")
    User selectByUsername(String username);

    /**
     * 按照用户id查询
     *
     * @param id
     * @return
     */
    User selectById(Integer id);
}
