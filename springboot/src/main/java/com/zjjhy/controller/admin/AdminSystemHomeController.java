package com.zjjhy.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.annotation.Log;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.pojo.dto.DocsDto;
import com.zjjhy.pojo.vo.DocsVo;
import com.zjjhy.pojo.vo.PageVo;
import com.zjjhy.pojo.vo.Result;
import com.zjjhy.service.AdminSystemHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docs")
@Slf4j
public class AdminSystemHomeController {
    @Autowired
    private AdminSystemHomeService adminSystemHomeService;

    /**
     * 分页查询文档
     *
     * @param docsDto
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/queryDocsData")
    public Result queryByDocsTitle(DocsDto docsDto, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("条件查询文档：{},{},{}", docsDto, page, pageSize);
        PageVo<DocsVo> pageVo = adminSystemHomeService.queryByDocsTitle(docsDto, page, pageSize);
        return Result.success(pageVo);
    }

    /**
     * 添加文档
     *
     * @param dto
     * @return
     */
    @Log
    @PostMapping
    public Result addDocs(@RequestBody DocsDto dto) {
        log.info("添加文档：{}", dto);
        int i = adminSystemHomeService.addDocs(dto);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 批量删除文章
     *
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping
    public Result deleteDocs(@RequestBody List<Integer> ids) {
        log.info("删除文档：{}", ids);
        int i = adminSystemHomeService.deleteDocs(ids);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    @Log
    @PutMapping
    public Result updateDocs(@RequestBody DocsDto dto) {
        log.info("编辑文档：{}", dto);
        int i = adminSystemHomeService.updateDocs(dto);
        if (i > 0) {
            return Result.success();
        }
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 效验数据是否存在
     *
     * @param docsDto
     * @return
     */
    @PostMapping("/validateDocs")
    public Result validateDocs(@RequestBody DocsDto docsDto) {
        // 空对象返回参数缺失
        if (ObjectUtil.isEmpty(docsDto.getDocsTitle())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        List<DocsVo> list = adminSystemHomeService.validateDocs(docsDto);
        //不存在返回200通过规则效验
        if (ObjectUtil.isEmpty(list)) {
            return Result.success(ResultCodeEnum.SUCCESS);
        }
        return Result.error(ResultCodeEnum.DOCS_EXIST_ERROR);
    }
}
