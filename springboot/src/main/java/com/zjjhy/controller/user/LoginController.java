package com.zjjhy.controller.user;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.vo.Result;
import com.zjjhy.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        if (ObjectUtil.isEmpty(userDto.getUsername()) || ObjectUtil.isEmpty(userDto.getPwd())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        return Result.success(loginService.login(userDto));
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        if (ObjectUtil.isEmpty(userDto.getUsername()) || ObjectUtil.isEmpty(userDto.getPwd())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        int i = loginService.register(userDto);
        if (i > 0) {
            return Result.success();
        }

        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }
}
