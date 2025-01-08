package com.example.service;

import com.example.common.pojo.dto.DocsDto;
import com.example.common.pojo.vo.PageVo;

public interface FrontService {
    /**
     * 前台用户获取数据
     *
     * @param docsDto
     * @param page
     * @param pageSize
     * @return
     */
    PageVo getDocs(DocsDto docsDto, Integer page, Integer pageSize);
}
