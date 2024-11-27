package com.zjjhy.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.Result;
import com.zjjhy.common.annotation.Log;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.pojo.dto.PwdDto;
import com.zjjhy.pojo.dto.UserDto;
import com.zjjhy.pojo.entity.User;
import com.zjjhy.pojo.vo.PageVo;
import com.zjjhy.pojo.vo.PwdVo;
import com.zjjhy.pojo.vo.UserVo;
import com.zjjhy.service.AdminSystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class AdminSystemUserController {
    @Autowired
    private AdminSystemUserService adminSystemUserService;

    /**
     * 分页查询
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/queryUserData")
    public Result queryUserData(User user,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("分页查询{}，{}，{}", user, page, pageSize);
        PageVo pageVo = adminSystemUserService.queryUserData(user, page, pageSize);
        return Result.success(pageVo);
    }

    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    @Log
    @PostMapping
    public Result addDocs(@RequestBody UserDto userDto) {
        log.info("添加用户：{}", userDto);
        int i = adminSystemUserService.addUser(userDto);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping
    public Result deleteDocs(@RequestBody List<Integer> ids) {
        log.info("删除用户：{}", ids);
        int i = adminSystemUserService.deleteUser(ids);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 编辑用户
     *
     * @param userDto
     * @return
     */
    @Log
    @PutMapping
    public Result updateDocs(@RequestBody UserDto userDto) {
        log.info("编辑用户：{}", userDto);
        int i = adminSystemUserService.updateUser(userDto);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 后台用户修改个人信息
     *
     * @param userDto
     * @return
     */
    @Log
    @PutMapping("/personCenter")
    public Result personCenter(@RequestBody UserDto userDto) {
        log.info("个人中心修改信息：{}", userDto);
        int i = adminSystemUserService.personCenter(userDto);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 后台用户获取密码
     *
     * @return
     */
    @Log
    @GetMapping("/PersonPwd")
    public Result getPersonPwd(@RequestParam("id") Integer id) {
        log.info("后台用户获取密码：{}", id);
        PwdVo pwd = adminSystemUserService.getPersonPwd(id);
        if (!pwd.getPwd().isEmpty()) {
            return Result.success(pwd);
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 后台用户修改密码
     *
     * @return
     */
    @Log
    @PutMapping("/PersonPwd")
    public Result updatePwd(@RequestBody PwdDto pwdDto) {
        log.info("后台用户修改密码：{}", pwdDto);
        int i = adminSystemUserService.updatePwd(pwdDto);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 效验数据是否存在
     *
     * @param userDto
     * @return
     */
    @PostMapping("/validateUser")
    public Result validateUser(@RequestBody UserDto userDto) {
        log.info("验证用户信息：{}", userDto);
        if (ObjectUtil.isEmpty(userDto.getUsername())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        List<UserVo> list = adminSystemUserService.validateUser(userDto);
        if (ObjectUtil.isEmpty(list)) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.USER_EXIST_ERROR);
    }
}
