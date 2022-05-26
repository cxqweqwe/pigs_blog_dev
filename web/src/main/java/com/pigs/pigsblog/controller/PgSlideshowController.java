package com.pigs.pigsblog.controller;


import com.pigs.pigsblog.entity.PgSlideshow;
import com.pigs.pigsblog.model.ResultFormat;
import com.pigs.pigsblog.model.ResultFormatPaging;
import com.pigs.pigsblog.service.PgSlideshowService;
import com.pigs.pigsblog.util.ResultPagingUtil;
import com.pigs.pigsblog.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 轮播图 前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-04-17
 */
@RestController
@RequestMapping("/pgSlideshow/")
@Api(value = "/pgSlideshow", description = "轮播图前端控制器")
public class PgSlideshowController {

    @Autowired
    private PgSlideshowService pgSlideshowService;

    /**
     * 查询轮播图
     *
     * @return
     */
    @GetMapping("queryPgSlideshow")
    public ResultFormat queryPgSlideshow() {
        List<PgSlideshow> pgSlideshowList = pgSlideshowService.queryPgSlideshow();
        return ResultUtil.success(pgSlideshowList);
    }

    /**
     * 查询图库列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("queryGalleryPage")
    @ApiOperation(value = "查询图库列表")
    public ResultFormatPaging queryPgArticles(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        if (page != null && limit != null) {
            ResultFormatPaging resultFormatPaging = pgSlideshowService.queryGalleryPage(page, limit);
            return ResultPagingUtil.pagingSuccess(resultFormatPaging.getCode(), resultFormatPaging.getCount(), resultFormatPaging.getData());
        }
        return ResultPagingUtil.pagingSuccess(0, 0L, "查询失败");

    }


}
