package com.pigs.pigsblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.pigsblog.entity.PgArticles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigs.pigsblog.entity.PgUsers;
import com.pigs.pigsblog.model.ResultFormat;
import com.pigs.pigsblog.model.ResultFormatPaging;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
public interface PgArticlesService extends IService<PgArticles> {

    /**
     * 查询文章并分页显示
     * 条件查询
     * @param page
     * @param limit
     * @return
     */
    IPage<PgArticles> queryPgArticles(Integer page, Integer limit, PgArticles pgArticles);

    /**
     * 查询文章排行榜
     *
     * @return
     */
    PgArticles queryPgArticleViews();

}
