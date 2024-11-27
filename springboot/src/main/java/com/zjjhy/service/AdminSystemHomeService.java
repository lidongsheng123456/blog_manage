package com.zjjhy.service;

import com.zjjhy.pojo.dto.DocsDto;
import com.zjjhy.pojo.vo.DocsVo;
import com.zjjhy.pojo.vo.PageVo;

import java.util.List;

public interface AdminSystemHomeService {
    PageVo<DocsVo> queryDocsData(Integer page, Integer pageSize);

    int addDocs(DocsDto dto);

    int updateDocs(DocsDto dto);

    int deleteDocs(List<Integer> ids);

    PageVo<DocsVo> queryByDocsTitle(DocsDto docsDto, Integer page, Integer pageSize);

    List<DocsVo> validateDocs(DocsDto docsDto);
}
