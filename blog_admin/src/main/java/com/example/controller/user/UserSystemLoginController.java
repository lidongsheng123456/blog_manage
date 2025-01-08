package com.example.controller.user;

import com.example.common.pojo.dto.UserDto;
import com.example.common.pojo.entity.User;
import com.example.common.pojo.vo.Result;
import com.example.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserSystemLoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        log.info("用户登录：{}", userDto);
        User user = loginService.login(userDto);
        return Result.success(user);
    }

    /**
     * 注册
     *
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        log.info("用户注册：{}", userDto);
        loginService.register(userDto);
        return Result.success();
    }
}
