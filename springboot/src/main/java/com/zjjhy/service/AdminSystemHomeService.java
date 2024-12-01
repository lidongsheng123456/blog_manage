package com.zjjhy.service;

import com.zjjhy.common.pojo.dto.DocsDto;
import com.zjjhy.common.pojo.vo.DocsVo;
import com.zjjhy.common.pojo.vo.PageVo;

import java.util.List;

public interface AdminSystemHomeService {

    /**
     * 添加文档
     *
     * @param dto
     */
    void addDocs(DocsDto dto);

    /**
     * 删除文档
     *
     * @param ids
     */
    void deleteDocs(List<Integer> ids);

    /**
     * 编辑文档
     *
     * @param dto
     */
    void updateDocs(DocsDto dto);

    /**
     * 查询文档
     *
     * @param docsDto
     * @param page
     * @param pageSize
     */
    PageVo<DocsVo> queryByDocsTitle(DocsDto docsDto, Integer page, Integer pageSize);

    /**
     * 验证文档表单
     *
     * @param docsDto
     */
    void validateDocs(DocsDto docsDto);
}
