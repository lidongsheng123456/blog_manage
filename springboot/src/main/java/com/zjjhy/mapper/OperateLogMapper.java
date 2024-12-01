package com.zjjhy.mapper;

import com.zjjhy.common.pojo.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    /**
     * 记录增删改日志
     */
    @Insert("insert into operate_log(operate_user_id,operate_time,class_name,method_name,method_params,return_value,cost_time)" +
            "values(#{operateUserId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insertLog(OperateLog operateLog);
}
