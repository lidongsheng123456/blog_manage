package com.example.web.controller.system.admin;

import com.example.system.domain.User;
import com.example.system.domain.dto.PwdDto;
import com.example.system.domain.dto.UserDto;
import com.example.system.domain.vo.PageVo;
import com.example.system.domain.vo.PwdVo;
import com.example.system.domain.vo.Result;
import com.example.system.service.AdminSystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Slf4j
public class SystemUserController {
    @Autowired
    private AdminSystemUserService adminSystemUserService;

    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    @PostMapping
    public Result addDocs(@RequestBody UserDto userDto) {
        log.info("添加用户：{}", userDto);
        adminSystemUserService.addUser(userDto);
        return Result.success();
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result deleteDocs(@RequestBody List<Integer> ids) {
        log.info("删除用户：{}", ids);
        adminSystemUserService.deleteUser(ids);
        return Result.success();
    }

    /**
     * 编辑用户
     *
     * @param userDto
     * @return
     */
    @PutMapping
    public Result updateDocs(@RequestBody UserDto userDto) {
        log.info("编辑用户：{}", userDto);
        adminSystemUserService.updateUser(userDto);
        return Result.success();
    }

    /**
     * 查询用户
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
     * 后台用户修改个人信息
     *
     * @param userDto
     * @return
     */
    @PutMapping("/personCenter")
    public Result personCenter(@RequestBody UserDto userDto) {
        log.info("个人中心修改信息：{}", userDto);
        adminSystemUserService.personCenter(userDto);
        return Result.success();
    }

    /**
     * 后台用户获取密码
     *
     * @return
     */
    @GetMapping("/PersonPwd")
    public Result getPersonPwd(@RequestParam("id") Integer id) {
        log.info("后台用户获取密码：{}", id);
        PwdVo pwd = adminSystemUserService.getPersonPwd(id);
        return Result.success(pwd);
    }

    /**
     * 后台用户修改密码
     *
     * @return
     */
    @PutMapping("/PersonPwd")
    public Result updatePwd(@RequestBody PwdDto pwdDto) {
        log.info("后台用户修改密码：{}", pwdDto);
        adminSystemUserService.updatePwd(pwdDto);
        return Result.success();
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
        adminSystemUserService.validateUser(userDto);
        return Result.success();
    }
}
