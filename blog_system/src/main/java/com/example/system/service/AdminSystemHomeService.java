package com.example.system.service;


import com.example.system.domain.dto.DocsDto;
import com.example.system.domain.vo.DocsVo;
import com.example.system.domain.vo.PageVo;

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
