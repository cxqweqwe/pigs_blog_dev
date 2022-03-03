package com.pigs.pigsblog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.pigsblog.entity.PgArticles;
import com.pigs.pigsblog.entity.PgSlideshow;
import com.pigs.pigsblog.entity.PgUsers;
import com.pigs.pigsblog.mapper.PgArticlesMapper;
import com.pigs.pigsblog.model.ResultFormat;
import com.pigs.pigsblog.model.ResultFormatPaging;
import com.pigs.pigsblog.service.PgArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigs.pigsblog.util.RedisUtil;
import com.pigs.pigsblog.util.ResultPagingUtil;
import com.pigs.pigsblog.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章（博文） 服务实现类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@Service
@Transactional
public class PgArticlesServiceImpl extends ServiceImpl<PgArticlesMapper, PgArticles> implements PgArticlesService {

    private Logger logger = LoggerFactory.getLogger(PgArticlesServiceImpl.class);

    @Autowired
    private PgArticlesMapper pgArticlesMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询文章并分页显示 实现方法
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public IPage<PgArticles> queryPgArticles(Integer page, Integer limit, PgArticles pgArticles) {

        if (page != null && limit != null) {

            /**
             * 如果上面的 redis中没有数据就进入mysql查询
             */
            Page<PgArticles> iPage = new Page<PgArticles>(page, limit);
            IPage<PgArticles> mapIPage = pgArticlesMapper.queryPgArticles(iPage, pgArticles);
            logger.info("mapIPage={}", mapIPage);
            return mapIPage;
        }

        return null;
    }

    /**
     * 查询文章排行榜 实习类
     *
     * @return
     */
    @Override
    public PgArticles queryPgArticleViews() {

        PgArticles pgArticles = pgArticlesMapper.queryPgArticleViews();

        return pgArticles;
    }


}
