package com.example.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.annotation.AutoFill;
import com.example.common.annotation.Log;
import com.example.common.enums.OperationTypeEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.exception.BusinessException;
import com.example.common.interface_constants.Constants;
import com.example.system.domain.Docs;
import com.example.system.domain.dto.DocsDto;
import com.example.system.domain.vo.DocsVo;
import com.example.system.domain.vo.PageVo;
import com.example.system.mapper.AdminSystemHomeMapper;
import com.example.system.service.AdminSystemHomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSystemHomeServiceImpl implements AdminSystemHomeService {

    @Autowired
    private AdminSystemHomeMapper adminSystemHomeMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加文档
     *
     * @param dto
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.INSERT)//自动注入公共字段
    @Override
    public void addDocs(DocsDto dto) {
        Docs docs = new Docs();
        BeanUtils.copyProperties(dto, docs);
        int i = adminSystemHomeMapper.addDocs(docs);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
        //删除redis存储的旧文档数据
        redisTemplate.delete(Constants.REDIS_KEY);
    }

    /**
     * 删除文档
     *
     * @param ids
     */
    @Log//记录操作日志
    @Override
    public void deleteDocs(List<Integer> ids) {
        int i = adminSystemHomeMapper.deleteDocs(ids);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
        //删除redis存储的旧文档数据
        redisTemplate.delete(Constants.REDIS_KEY);
    }

    /**
     * 编辑文档
     *
     * @param dto
     */
    @Log//记录操作日志
    @AutoFill(OperationTypeEnum.UPDATE)//自动注入公共字段
    @Override
    public void updateDocs(DocsDto dto) {
        int i = adminSystemHomeMapper.updateDocs(dto);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
        //删除redis存储的旧文档数据
        redisTemplate.delete(Constants.REDIS_KEY);
    }

    /**
     * 查询文档
     *
     * @param docsDto
     * @param page
     * @param pageSize
     */
    @Override
    public PageVo<DocsVo> queryByDocsTitle(DocsDto docsDto, Integer page, Integer pageSize) {
        Docs docs = new Docs();
        BeanUtils.copyProperties(docsDto, docs);
        PageVo<DocsVo> pageVo = new PageVo<>();
        pageVo.setTotal(adminSystemHomeMapper.queryByDocsTotal(docs));
        pageVo.setData(adminSystemHomeMapper.queryByDocsData(docs, (page - 1) * pageSize, pageSize));
        return pageVo;
    }

    /**
     * 验证文档表单
     *
     * @param docsDto
     */
    @Override
    public void validateDocs(DocsDto docsDto) {
        // 空对象返回参数缺失
        if (ObjectUtil.isEmpty(docsDto.getDocsTitle())) {
            throw new BusinessException(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        Docs docs = new Docs();
        BeanUtils.copyProperties(docsDto, docs);
        List<DocsVo> list = adminSystemHomeMapper.docsDto(docs);
        //存在抛出数据已存在
        if (ObjectUtil.isNotEmpty(list)) {
            throw new BusinessException(ResultCodeEnum.DOCS_EXIST_ERROR);
        }
    }
}
