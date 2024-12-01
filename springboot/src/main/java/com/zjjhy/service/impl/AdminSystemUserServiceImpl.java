package com.zjjhy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.annotation.AutoFill;
import com.zjjhy.common.annotation.Log;
import com.zjjhy.common.enums.OperationTypeEnum;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.common.interface_constants.Constants;
import com.zjjhy.mapper.AdminSystemHomeMapper;
import com.zjjhy.mapper.AdminSystemUserMapper;
import com.zjjhy.common.pojo.dto.PwdDto;
import com.zjjhy.common.pojo.dto.UserDto;
import com.zjjhy.common.pojo.entity.User;
import com.zjjhy.common.pojo.vo.PageVo;
import com.zjjhy.common.pojo.vo.PwdVo;
import com.zjjhy.common.pojo.vo.UserVo;
import com.zjjhy.service.AdminSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminSystemUserServiceImpl implements AdminSystemUserService {
    @Autowired
    private AdminSystemUserMapper adminSystemUserMapper;

    @Autowired
    private AdminSystemHomeMapper adminSystemHomeMapper;

    /**
     * 添加用户
     *
     * @param userDto
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.INSERT)//自动注入公共字段
    @Override
    public void addUser(UserDto userDto) {
        userDto.setPwd(Constants.USER_DEFAULT_PASSWORD);
        int i = adminSystemUserMapper.addUser(userDto);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 删除用户
     *
     * @param ids
     */
    @Log//记录操作日志
    @Transactional//开启事务要么删除用户和文章一起成功要么一起失败
    @Override
    public void deleteUser(List<Integer> ids) {
        int i1 = adminSystemUserMapper.deleteUser(ids);
        //删除当前用户对应的文章
        int i2 = adminSystemHomeMapper.deleteDocsByUserId(ids);
        if (i1 == 0 || i2 == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 编辑用户
     *
     * @param userDto
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.UPDATE)//自动注入公共字段
    @Override
    public void updateUser(UserDto userDto) {
        int i = adminSystemUserMapper.updateUser(userDto);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 查询用户
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageVo queryUserData(User user, Integer page, Integer pageSize) {
        PageVo<UserVo> pageVo = new PageVo<>();
        pageVo.setTotal(adminSystemUserMapper.queryUserTotal(user));
        pageVo.setData(adminSystemUserMapper.queryUserData(user, (page - 1) * pageSize, pageSize));
        return pageVo;
    }

    /**
     * 编辑个人信息
     *
     * @param userDto
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.UPDATE)//自动注入公共字段
    @Override
    public void personCenter(UserDto userDto) {
        int i = adminSystemUserMapper.personCenter(userDto);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 查询个人密码
     *
     * @param id
     * @return
     */
    @Override
    public PwdVo getPersonPwd(Integer id) {
        PwdVo pwd = adminSystemUserMapper.getPersonPwd(id);
        if (pwd.getPwd().isEmpty()) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return pwd;
    }

    /**
     * 修改个人密码
     *
     * @param pwdDto
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.UPDATE)//自动注入公共字段
    @Override
    public void updatePwd(PwdDto pwdDto) {
        int i = adminSystemUserMapper.updatePwd(pwdDto);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 效验个人信息
     *
     * @param userDto
     */
    @Override
    public void validateUser(UserDto userDto) {
        if (ObjectUtil.isEmpty(userDto.getUsername())) {
            throw new BusinessException(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        List<UserVo> userVo = adminSystemUserMapper.queryByUsername(userDto);
        if (!ObjectUtil.isEmpty(userVo)) {
            throw new BusinessException(ResultCodeEnum.USER_EXIST_ERROR);
        }
    }
}
