package com.zjjhy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.common.interface_constants.Constants;
import com.zjjhy.common.pojo.dto.DocsDto;
import com.zjjhy.common.pojo.entity.Docs;
import com.zjjhy.common.pojo.vo.DocsVo;
import com.zjjhy.common.pojo.vo.PageVo;
import com.zjjhy.mapper.AdminSystemHomeMapper;
import com.zjjhy.service.FrontService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FrontServiceImpl implements FrontService {

    @Autowired
    private AdminSystemHomeMapper adminSystemHomeMapper;

    @Autowired
    private RedisTemplate<String, DocsVo> redisTemplate;

    /**
     * 前台用户获取数据
     *
     * @param docsDto
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageVo<DocsVo> getDocs(DocsDto docsDto, Integer page, Integer pageSize) {
        //分页返回结果
        PageVo<DocsVo> pageVo = new PageVo<>();

        //redis存储文章列表的键
        String redisKey = Constants.REDIS_KEY;

        //获取操作redis的列表数据对象
        ListOperations<String, DocsVo> list = redisTemplate.opsForList();

        //使用range拿到所有范围数据(能取到结束索引的值，不是左闭右开)
        List<DocsVo> range = list.range(redisKey, 0, -1);

        //1.如果从redis查出来的数据不是空,则处理后直接返回redis中的数据
        if (ObjectUtil.isNotEmpty(range)) {
            int beginPage = (page - 1) * pageSize;

            int endPage = Math.min((beginPage + pageSize), range.size());

            //按照id排序避免顺序混乱
            range.sort(Comparator.comparingInt(DocsVo::getId));

            try {
                //如果形参对象的文档标题是空不用判断直接把redis返回的数据返回
                if (ObjectUtil.isEmpty(docsDto.getDocsTitle())) {
                    pageVo.setData(range.subList(beginPage, endPage));//subList()左闭右开
                    pageVo.setTotal(range.size());
                } else {
                    //如果形参对象的文档标题不是空直接需要用contains方法返回符合条件的数据
                    List<DocsVo> list1 = new ArrayList<>();
                    for (DocsVo next : range) {
                        if (next.getDocsTitle().contains(docsDto.getDocsTitle())) {
                            list1.add(next);
                        }
                    }
                    //过滤过的结束索引需重新计算
                    int endPage2 = Math.min((beginPage + pageSize), list1.size());
                    //把处理好的数据赋值给pageVo准备响应前端
                    pageVo.setData(list1.subList(beginPage, endPage2));
                    pageVo.setTotal(list1.size());
                }

                return pageVo;
            } catch (NullPointerException e) {
                //抛出业务异常传递一个Throwable对象作为其他原因
                throw new BusinessException(ResultCodeEnum.SYSTEM_ERROR, e);
            }
        }

        //2.redis没有数据，调用mapper层查询返回
        Docs docs = new Docs();
        BeanUtils.copyProperties(docsDto, docs);
        pageVo.setTotal(adminSystemHomeMapper.queryByDocsTotal(docs));
        pageVo.setData(adminSystemHomeMapper.queryByDocsData(docs, (page - 1) * pageSize, pageSize));
        //将查询到的数据批量插入redis，后续就不用调用mysql查询
        list.leftPushAll(redisKey, adminSystemHomeMapper.queryDocsData());

        return pageVo;
    }
}
