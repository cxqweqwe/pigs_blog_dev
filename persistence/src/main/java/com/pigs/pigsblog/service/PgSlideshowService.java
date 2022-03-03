package com.pigs.pigsblog.service;

import com.pigs.pigsblog.entity.PgSlideshow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigs.pigsblog.model.ResultFormatPaging;

import java.util.List;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-17
 */
public interface PgSlideshowService extends IService<PgSlideshow> {

    /**
     * 查询轮播图  最新的5条数据
     * @return
     */
    List<PgSlideshow> queryPgSlideshow();

    /**
     * 查询 图库显示页面 并分页
     *
     * @param page
     * @param limit
     * @return
     */
    ResultFormatPaging queryGalleryPage(Integer page, Integer limit);

}
