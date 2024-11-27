package com.zjjhy.controller.common;

import com.zjjhy.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

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
