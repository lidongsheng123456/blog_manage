package com.example.web.controller.system.admin;

import com.example.system.domain.dto.DocsDto;
import com.example.system.domain.vo.PageVo;
import com.example.system.domain.vo.Result;
import com.example.system.domain.vo.DocsVo;
import com.example.system.service.AdminSystemHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/docs")
@Slf4j
public class SystemHomeController {
    @Autowired
    private AdminSystemHomeService adminSystemHomeService;

    /**
     * 添加文档
     *
     * @param dto
     * @return
     */
    @PostMapping
    public Result addDocs(@RequestBody DocsDto dto) {
        log.info("添加文档：{}", dto);
        adminSystemHomeService.addDocs(dto);
        return Result.success();
    }

    /**
     * 批量删除文档
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result deleteDocs(@RequestBody List<Integer> ids) {
        log.info("删除文档：{}", ids);
        adminSystemHomeService.deleteDocs(ids);
        return Result.success();
    }

    /**
     * 编辑文档
     *
     * @param dto
     * @return
     */
    @PutMapping
    public Result updateDocs(@RequestBody DocsDto dto) {
        log.info("编辑文档：{}", dto);
        adminSystemHomeService.updateDocs(dto);
        return Result.success();
    }

    /**
     * 查询文档
     *
     * @param docsDto
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/queryDocsData")
    public Result queryByDocsTitle(DocsDto docsDto,
                                   @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("条件查询文档：{},{},{}", docsDto, page, pageSize);
        PageVo<DocsVo> docsVoPageVo = adminSystemHomeService.queryByDocsTitle(docsDto, page, pageSize);
        return Result.success(docsVoPageVo);
    }

    /**
     * 效验数据是否存在
     *
     * @param docsDto
     * @return
     */
    @PostMapping("/validateDocs")
    public Result validateDocs(@RequestBody DocsDto docsDto) {
        log.info("效验数据：{}", docsDto);
        adminSystemHomeService.validateDocs(docsDto);
        return Result.success();
    }
}
