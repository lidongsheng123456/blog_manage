package com.zjjhy.service.impl;

import com.zjjhy.mapper.AdminSystemHomeMapper;
import com.zjjhy.common.pojo.dto.DocsDto;
import com.zjjhy.common.pojo.entity.Docs;
import com.zjjhy.common.pojo.vo.DocsVo;
import com.zjjhy.common.pojo.vo.PageVo;
import com.zjjhy.service.FrontService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrontServiceImpl implements FrontService {

    @Autowired
    private AdminSystemHomeMapper adminSystemHomeMapper;

    /**
     * 前台用户获取数据
     * @param docsDto
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageVo getDocs(DocsDto docsDto, Integer page, Integer pageSize) {
        Docs docs = new Docs();
        BeanUtils.copyProperties(docsDto, docs);
        PageVo<DocsVo> pageVo = new PageVo<>();
        pageVo.setTotal(adminSystemHomeMapper.queryByDocsTotal(docs));
        pageVo.setData(adminSystemHomeMapper.queryByDocsData(docs, (page - 1) * pageSize, pageSize));
        return pageVo;
    }
}
