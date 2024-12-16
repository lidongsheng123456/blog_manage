package com.zjjhy.mapper;

import com.zjjhy.common.pojo.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    /**
     * 查询数据总数
     *
     * @param operateLog
     * @return
     */
    Integer queryByLogTotal(OperateLog operateLog);

    /**
     * 查询数据
     *
     * @param operateLog
     * @param page
     * @param pageSize
     * @return
     */
    List<OperateLog> queryByLogData(OperateLog operateLog, Integer page, Integer pageSize);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteById(List<Integer> id);

    /**
     * 记录增删改日志
     */
    @Insert("insert into operate_log(operate_user_id,operate_time,class_name,method_name,method_params,return_value,cost_time)" +
            "values(#{operateUserId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insertLog(OperateLog operateLog);
}
