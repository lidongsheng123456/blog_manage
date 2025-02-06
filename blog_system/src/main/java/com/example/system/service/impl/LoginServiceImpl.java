package com.example.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.annotation.AutoFill;
import com.example.common.annotation.Log;
import com.example.common.enums.OperationTypeEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.exception.BusinessException;
import com.example.common.interface_constants.Constants;
import com.example.common.properties.JwtProperties;
import com.example.common.util.JwtUtil;
import com.example.system.domain.User;
import com.example.system.domain.dto.UserDto;
import com.example.system.mapper.LoginMapper;
import com.example.system.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    @Log//记录操作日志
    @Override
    public User login(UserDto userDto, HttpSession session) {
        if (ObjectUtil.isEmpty(userDto.getUsername()) || ObjectUtil.isEmpty(userDto.getPwd()) || ObjectUtil.isEmpty(userDto.getCode())) {
            throw new BusinessException(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        Boolean b = validateCaptcha(userDto.getCode(), session);

        if (b) {
            throw new BusinessException(ResultCodeEnum.CAPTCHA_ERROR);
        }

        User user = loginMapper.selectByUsername(userDto.getUsername());

        //空返回true isEmpty会检查String list array 的长度是否为0 其他对象则检查是否为null
        if (ObjectUtil.isEmpty(user)) {
            throw new BusinessException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        if (!userDto.getPwd().equals(user.getPwd())) {
            throw new BusinessException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        if (Constants.ROLE.equals(user.getRole())) {
            //登录成功后，生成管理员token令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("empId", user.getId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getAdminSecretKey(),
                    jwtProperties.getAdminTtl(),
                    claims);
            user.setToken(token);
        } else {
            //登录成功后，生成用户token令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getUserSecretKey(),
                    jwtProperties.getUserTtl(),
                    claims);
            user.setToken(token);
        }
        return user;
    }

    /**
     * 注册
     *
     * @param userDto
     * @return
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.INSERT)//公共字段自动赋值
    @Override
    public void register(UserDto userDto) {
        if (ObjectUtil.isEmpty(userDto.getUsername()) || ObjectUtil.isEmpty(userDto.getPwd())) {
            throw new BusinessException(ResultCodeEnum.PARAM_LOST_ERROR);
        }

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
        int i = loginMapper.register(userDto);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
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

    public Boolean validateCaptcha(String captcha, HttpSession session) {
        // 获取存储的验证码和生成时间
        String code = (String) session.getAttribute(Constants.CAPTCHA_KEY);
        Date createTime = (Date) session.getAttribute(Constants.CAPTCHA_DATE);
        // 判断验证码是否正确(验证码一般忽略大小写)
        if (captcha.equalsIgnoreCase(code)) {
            // 判断验证码是否过时
            return createTime == null || System.currentTimeMillis() - createTime.getTime() > Constants.EXPIRATION_TIME;
        }
        return true;
    }
}
