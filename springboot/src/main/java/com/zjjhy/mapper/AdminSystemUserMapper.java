package com.zjjhy.mapper;

import com.zjjhy.common.pojo.dto.PwdDto;
import com.zjjhy.common.pojo.dto.UserDto;
import com.zjjhy.common.pojo.entity.User;
import com.zjjhy.common.pojo.vo.PwdVo;
import com.zjjhy.common.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminSystemUserMapper {
    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    int addUser(UserDto userDto);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    int deleteUser(List<Integer> ids);

    /**
     * 编辑用户
     *
     * @param userDto
     * @return
     */
    int updateUser(UserDto userDto);

    /**
     * 查询用户总数
     *
     * @param user
     * @return
     */
    Integer queryUserTotal(User user);

    /**
     * 查询用户
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    List<UserVo> queryUserData(User user, Integer page, Integer pageSize);

    /**
     * 修改个人数据
     *
     * @param userDto
     * @return
     */
    int personCenter(UserDto userDto);

    /**
     * 查询密码
     *
     * @param id
     * @return
     */
    PwdVo getPersonPwd(Integer id);

    /**
     * 修改密码
     *
     * @param pwdDto
     * @return
     */
    int updatePwd(PwdDto pwdDto);

    /**
     * 效验个人信息
     *
     * @param userDto
     * @return
     */
    @Select("select * from blog_account where username=#{username}")
    List<UserVo> queryByUsername(UserDto userDto);
}
