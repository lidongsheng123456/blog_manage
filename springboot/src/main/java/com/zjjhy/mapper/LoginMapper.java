package com.zjjhy.mapper;

import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    UserDto login(UserDto userDto);

    int register(UserDto userDto);

    @Select("select * from blog_account where username=#{username}")
    User selectByUsername(String username);

    User selectById(Integer id);
}
