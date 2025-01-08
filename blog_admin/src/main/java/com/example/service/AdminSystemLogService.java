package com.example.service;

import com.example.common.pojo.entity.OperateLog;
import com.example.common.pojo.vo.PageVo;

import java.util.List;

/**
 * (OperateLog)表服务接口
 *
 * @author makejava
 * @since 2024-12-07 14:15:45
 */
public interface AdminSystemLogService {

    /**
     * 分页查询
     *
     * @param operateLog
     * @param page
     * @param pageSize
     * @return
     */
    PageVo<OperateLog> queryByPage(OperateLog operateLog, Integer page, Integer pageSize);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    void deleteById(List<Integer> id);

}
