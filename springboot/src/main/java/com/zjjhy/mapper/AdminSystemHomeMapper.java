package com.zjjhy.mapper;

import com.zjjhy.common.annotation.Log;
import com.zjjhy.pojo.dto.DocsDto;
import com.zjjhy.pojo.entity.Docs;
import com.zjjhy.pojo.vo.DocsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminSystemHomeMapper {
    List<DocsVo> queryDocsData(Integer page, Integer pageSize);

    Integer queryTotal();

    int addDocs(Docs docs);

    int updateDocs(DocsDto dto);

    int deleteDocs(List<Integer> ids);

    Integer queryByDocsTotal(Docs docs);

    List<DocsVo> queryByDocsData(Docs docs, Integer page, Integer pageSize);

    @Select("select * from blog_docs where docs_title=#{docsTitle}")
    List<DocsVo> docsDto(Docs docs);
}
