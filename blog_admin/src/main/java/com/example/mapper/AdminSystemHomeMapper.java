package com.example.mapper;

import com.example.common.pojo.dto.DocsDto;
import com.example.common.pojo.entity.Docs;
import com.example.common.pojo.vo.DocsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminSystemHomeMapper {
    /**
     * 添加文档
     *
     * @param docs
     * @return
     */
    int addDocs(Docs docs);

    /**
     * 删除文档
     *
     * @param ids
     * @return
     */
    int deleteDocs(List<Integer> ids);

    /**
     * 删除当前用户对应的文章
     *
     * @param ids
     * @return
     */
    int deleteDocsByUserId(List<Integer> ids);

    /**
     * 编辑文档
     *
     * @param dto
     * @return
     */
    int updateDocs(DocsDto dto);

    /**
     * 查询文档总数
     *
     * @param docs
     * @return
     */
    Integer queryByDocsTotal(Docs docs);

    /**
     * 根据文档标题查询文档
     *
     * @param docs
     * @param page
     * @param pageSize
     * @return
     */
    List<DocsVo> queryByDocsData(Docs docs, Integer page, Integer pageSize);

    /**
     * 查询文章与用户名
     *
     * @return
     */
    List<DocsVo> queryDocsData();

    /**
     * 验证文档表单
     *
     * @param docs
     * @return
     */
    @Select("select * from blog_docs where docs_title=#{docsTitle}")
    List<DocsVo> docsDto(Docs docs);

    /**
     * 查询文档
     *
     * @return
     */
    @Select("select * from blog_docs")
    List<Docs> queryByUsernameDocsData();
}
