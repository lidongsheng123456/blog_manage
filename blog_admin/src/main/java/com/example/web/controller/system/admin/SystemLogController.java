package com.example.web.controller.system.admin;

import com.example.system.domain.OperateLog;
import com.example.system.domain.vo.Result;
import com.example.system.service.AdminSystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/operateLog")
public class SystemLogController {
    @Autowired
    private AdminSystemLogService adminSystemLogService;

    /**
     * 分页查询
     *
     * @param operateLog
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result queryByPage(OperateLog operateLog,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        log.info("查询日志，{},{},{}", operateLog, page, pageSize);
        return Result.success(adminSystemLogService.queryByPage(operateLog, page, pageSize));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(@RequestBody List<Integer> id) {
        log.info("删除日志：{}", id);
        adminSystemLogService.deleteById(id);
        return Result.success();
    }
}

