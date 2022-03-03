package com.pigs.pigsblog.service.impl;

import com.pigs.pigsblog.entity.PgUserFriends;
import com.pigs.pigsblog.mapper.PgUserFriendsMapper;
import com.pigs.pigsblog.service.PgUserFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigs.pigsblog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PgUserFriendsServiceImpl extends ServiceImpl<PgUserFriendsMapper, PgUserFriends> implements PgUserFriendsService {

    @Autowired
    private RedisUtil redisUtil;

}
