package com.example.web.controller.common;

import com.example.system.domain.User;
import com.example.system.domain.dto.UserDto;
import com.example.system.domain.vo.Result;
import com.example.system.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto, HttpSession session) {
        log.info("用户登录：{}", userDto);
        User user = loginService.login(userDto, session);
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

    /**
     * 基础接口
     *
     * @return
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }
}
