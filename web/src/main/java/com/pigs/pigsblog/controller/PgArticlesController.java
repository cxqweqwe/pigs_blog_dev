package com.pigs.pigsblog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pigs.pigsblog.entity.PgArticles;
import com.pigs.pigsblog.model.ResultFormat;
import com.pigs.pigsblog.model.ResultFormatPaging;
import com.pigs.pigsblog.service.PgArticlesService;
import com.pigs.pigsblog.util.ResultPagingUtil;
import com.pigs.pigsblog.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/pgArticles/")
@Api(value = "/pgArticles", description = "文章前端控制器")
public class PgArticlesController {

    private Logger logger = LoggerFactory.getLogger(PgArticlesController.class);

    @Autowired
    private PgArticlesService pgArticlesService;

    /**
     * 查询文章信息列表 并分页
     */
    @GetMapping("queryPgArticles")
    @ApiOperation(value = "查询文章信息列表")
    public ResultFormatPaging queryPgArticles(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, PgArticles pgArticles) {
        if (page != null && limit != null) {
            IPage<PgArticles> pgArticlesIPage = pgArticlesService.queryPgArticles(page, limit, pgArticles);
            List<PgArticles> pgArticlesList = pgArticlesIPage.getRecords();
            return ResultPagingUtil.pagingSuccess(0, pgArticlesIPage.getTotal(), pgArticlesList);
        }
        return ResultPagingUtil.pagingSuccess(0, 0L, "查询失败");

    }

    /**
     * 查询文章信息列表 并分页
     */
    @GetMapping("queryPgArticle")
    @ApiOperation(value = "查询文章信息列表")
    public ResultFormat queryPgArticles(PgArticles pgArticles) {
        logger.info("pgArticles={}", pgArticles);
        if (pgArticles.getUserId() != null && pgArticles.getArticleId() != null) {
            IPage<PgArticles> pgArticlesIPage = pgArticlesService.queryPgArticles(1, 1, pgArticles);
            List<PgArticles> pgArticlesList = pgArticlesIPage.getRecords();
            if (pgArticlesList.size() > 0 && pgArticlesList != null) {
                return ResultUtil.success(pgArticlesList);
            }
            return ResultUtil.success(100, "暂无数据..");
        }
        return ResultUtil.error(100, "参数不能为空！");
    }

    /**
     * 查询文章信息列表 并分页
     */
    @GetMapping("queryPgArticleViews")
    @ApiOperation(value = "查询文章信息列表")
    public ResultFormat queryPgArticleViews() {
        PgArticles pgArticles = pgArticlesService.queryPgArticleViews();
        if (pgArticles == null) {
            return ResultUtil.success(100, "暂无数据..");
        }
        return ResultUtil.success(pgArticles);
    }
}
