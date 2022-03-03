package com.pigs.pigsblog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.pigsblog.entity.PgArticles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * 文章（博文） Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@Repository
public interface PgArticlesMapper extends BaseMapper<PgArticles> {

    /**
     * 查询文章并分页显示
     * 条件查询等
     *
     * @param pagIng
     * @return
     */
    IPage<PgArticles> queryPgArticles(Page pagIng, @Param("pgArticles") PgArticles pgArticles);

    /**
     * 查询文章排行榜
     *
     * @return
     */
    PgArticles queryPgArticleViews();

}
