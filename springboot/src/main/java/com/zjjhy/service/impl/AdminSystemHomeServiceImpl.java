package com.zjjhy.service.impl;

import com.zjjhy.common.annotation.AutoFill;
import com.zjjhy.common.enums.OperationTypeEnum;
import com.zjjhy.mapper.AdminSystemHomeMapper;
import com.zjjhy.pojo.dto.DocsDto;
import com.zjjhy.pojo.entity.Docs;
import com.zjjhy.pojo.vo.DocsVo;
import com.zjjhy.pojo.vo.PageVo;
import com.zjjhy.service.AdminSystemHomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSystemHomeServiceImpl implements AdminSystemHomeService {

    @Autowired
    private AdminSystemHomeMapper adminSystemHomeMapper;

    @Override
    public PageVo<DocsVo> queryDocsData(Integer page, Integer pageSize) {
        PageVo<DocsVo> pageVo = new PageVo<>();
        pageVo.setTotal(adminSystemHomeMapper.queryTotal());
        pageVo.setData(adminSystemHomeMapper.queryDocsData((page - 1) * pageSize, pageSize));
        return pageVo;
    }

    @AutoFill(OperationTypeEnum.INSERT)
    @Override
    public int addDocs(DocsDto dto) {
        Docs docs = new Docs();
        BeanUtils.copyProperties(dto, docs);
        return adminSystemHomeMapper.addDocs(docs);
    }

    @AutoFill(OperationTypeEnum.UPDATE)
    @Override
    public int updateDocs(DocsDto dto) {
        return adminSystemHomeMapper.updateDocs(dto);
    }

    @Override
    public int deleteDocs(List<Integer> ids) {
        return adminSystemHomeMapper.deleteDocs(ids);
    }

    @Override
    public PageVo<DocsVo> queryByDocsTitle(DocsDto docsDto, Integer page, Integer pageSize) {
        Docs docs = new Docs();
        BeanUtils.copyProperties(docsDto, docs);
        PageVo<DocsVo> pageVo = new PageVo<>();
        pageVo.setTotal(adminSystemHomeMapper.queryByDocsTotal(docs));
        pageVo.setData(adminSystemHomeMapper.queryByDocsData(docs, (page - 1) * pageSize, pageSize));
        return pageVo;
    }

    @Override
    public List<DocsVo> validateDocs(DocsDto docsDto) {
        Docs docs = new Docs();
        BeanUtils.copyProperties(docsDto,docs);
        return adminSystemHomeMapper.docsDto(docs);
    }
}
