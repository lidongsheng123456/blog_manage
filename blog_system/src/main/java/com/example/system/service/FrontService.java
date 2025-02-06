package com.example.system.service;


import com.example.system.domain.dto.DocsDto;
import com.example.system.domain.vo.PageVo;

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
