package com.pigs.pigsblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.pigsblog.entity.PgArticles;
import com.pigs.pigsblog.entity.PgSlideshow;
import com.pigs.pigsblog.mapper.PgSlideshowMapper;
import com.pigs.pigsblog.model.ResultFormatPaging;
import com.pigs.pigsblog.service.PgSlideshowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigs.pigsblog.util.RedisUtil;
import com.pigs.pigsblog.util.ResultPagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-17
 */
@Service
@Transactional
public class PgSlideshowServiceImpl extends ServiceImpl<PgSlideshowMapper, PgSlideshow> implements PgSlideshowService {

    @Autowired
    private PgSlideshowMapper pgSlideshowMapper;

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(PgSlideshowServiceImpl.class);

    /**
     * 查询轮播图实现方法
     * 最新的三条数据
     *
     * @return
     */
    @Override
    public List<PgSlideshow> queryPgSlideshow() {

        /**
         * 实现先从redis中获取数据，如果发现没有数据那就会进入mysql中查询
         */
        List<Object> redisListId = redisUtil.lGet("pgSlideshowId", 0, 5);

        /**
         * 从redis中获取 轮播图的id
         * 然后判断这个id是否为null与上是否大于0，然后小于0 则直接进入mysql中查询
         * 判断id获取数据库如果不为null就存入集合中
         */
        if (redisListId != null && redisListId.size() > 0) {
            logger.info("轮播图id={}", redisListId);
            List<PgSlideshow> pgSlideshowListNew = new ArrayList<>();
            for (Object o : redisListId) {
                PgSlideshow pgSlideshow = (PgSlideshow) redisUtil.get("pgSlideshow:" + o);

                if (pgSlideshow != null) {
                    pgSlideshowListNew.add(pgSlideshow);
                }
            }

            /**
             * 判断集合中的数据是否为null与上是否大于0
             * 如果大于0那就返回数据回去前台
             */
            if (pgSlideshowListNew != null && pgSlideshowListNew.size() > 0) {
                logger.info("pgSlideshowListNew={}", pgSlideshowListNew);
                return pgSlideshowListNew;
            }
        }

        /**
         * 从mysql中获取数据
         */
        AbstractWrapper wrapper = new QueryWrapper<PgSlideshow>();
        wrapper.orderByDesc("slideshow_time");
        wrapper.last("limit 5");
        List<PgSlideshow> pgSlideshowList = pgSlideshowMapper.selectList(wrapper);
        if (pgSlideshowList.size() > 0) {
            /**
             * 删除redis缓存轮播图中的数据跟id
             */
            List<Integer> getSlideshowIds = pgSlideshowList.stream().map(PgSlideshow::getSlideshowId).collect(Collectors.toList());
            for (Integer getSlideshowId : getSlideshowIds) {
                redisUtil.del("pgSlideshow:" + getSlideshowId);
            }
            redisUtil.del("pgSlideshowId");

            /**
             * 然后从新赋值
             */
            for (PgSlideshow pgSlideshow : pgSlideshowList) {
                redisUtil.lSet("pgSlideshowId", pgSlideshow.getSlideshowId());
                redisUtil.set("pgSlideshow:" + pgSlideshow.getSlideshowId(), pgSlideshow);
            }

            return pgSlideshowList;
        }
        return null;
    }

    /**
     * 查询 图库显示页面 并分页
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public ResultFormatPaging queryGalleryPage(Integer page, Integer limit) {

        Page<PgSlideshow> iPage = new Page<PgSlideshow>(page, limit);
        IPage<PgSlideshow> mapIPage = pgSlideshowMapper.selectPage(iPage, null);
        List<PgSlideshow> pgArticlesList = iPage.getRecords();

        return ResultPagingUtil.pagingSuccess(0, iPage.getTotal(), pgArticlesList);
    }
}
