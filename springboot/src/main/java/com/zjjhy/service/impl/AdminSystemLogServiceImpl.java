package com.zjjhy.service.impl;

import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.common.pojo.entity.OperateLog;
import com.zjjhy.common.pojo.vo.PageVo;
import com.zjjhy.mapper.OperateLogMapper;
import com.zjjhy.service.AdminSystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSystemLogServiceImpl implements AdminSystemLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 分页查询
     *
     * @param operateLog
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageVo<OperateLog> queryByPage(OperateLog operateLog, Integer page, Integer pageSize) {
        PageVo<OperateLog> pageVo = new PageVo<>();
        pageVo.setTotal(operateLogMapper.queryByLogTotal(operateLog));
        pageVo.setData(operateLogMapper.queryByLogData(operateLog, (page - 1) * pageSize, pageSize));
        return pageVo;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(List<Integer> id) {
        int i = operateLogMapper.deleteById(id);
        if (i == 0) {
            throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
