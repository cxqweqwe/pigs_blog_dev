package com.pigs.pigsblog.service.impl;

import com.pigs.pigsblog.entity.PgComments;
import com.pigs.pigsblog.mapper.PgCommentsMapper;
import com.pigs.pigsblog.service.PgCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@Service
public class PgCommentsServiceImpl extends ServiceImpl<PgCommentsMapper, PgComments> implements PgCommentsService {

}
