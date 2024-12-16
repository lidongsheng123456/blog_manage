package com.zjjhy.controller.user;

import com.zjjhy.common.pojo.dto.DocsDto;
import com.zjjhy.common.pojo.vo.Result;
import com.zjjhy.service.FrontService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/front")
public class UserSystemFrontController {
    @Autowired
    private FrontService frontService;

    /**
     * 前台用户获取数据
     *
     * @param docsDto
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result getDocs(DocsDto docsDto,
                          @RequestParam("page") Integer page,
                          @RequestParam("pageSize") Integer pageSize) {
        log.info("前台用户获取数据：{},{},{}", docsDto, page, pageSize);
        return Result.success(frontService.getDocs(docsDto, page, pageSize));
    }
}
