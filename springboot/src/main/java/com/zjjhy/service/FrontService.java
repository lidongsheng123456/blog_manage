package com.zjjhy.service;

import com.zjjhy.pojo.dto.DocsDto;
import com.zjjhy.pojo.vo.PageVo;

public interface FrontService {
    PageVo getDocs(DocsDto docsDto, Integer page, Integer pageSize);
}
