package com.zjjhy.mapper;

import com.zjjhy.pojo.dto.PwdDto;
import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;
import com.zjjhy.pojo.vo.PwdVo;
import com.zjjhy.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminSystemUserMapper {
    List<UserVo> queryUserData(User user, Integer page, Integer pageSize);

    Integer queryUserTotal(User user);

    int addUser(UserDto userDto);

    int deleteUser(List<Integer> ids);

    int updateUser(UserDto userDto);

    int personCenter(UserDto userDto);

    PwdVo getPersonPwd(Integer id);

    int updatePwd(PwdDto pwdDto);

    @Select("select * from blog_account where username=#{username}")
    List<UserVo> queryByUsername(UserDto userDto);
}
