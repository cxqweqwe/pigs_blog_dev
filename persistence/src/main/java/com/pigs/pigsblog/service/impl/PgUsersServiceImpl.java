package com.pigs.pigsblog.service.impl;

import com.pigs.pigsblog.entity.PgUsers;
import com.pigs.pigsblog.mapper.PgUsersMapper;
import com.pigs.pigsblog.service.PgUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@Service
@Transactional
public class PgUsersServiceImpl extends ServiceImpl<PgUsersMapper, PgUsers> implements PgUsersService {

    @Autowired
    private PgUsersMapper pgUsersMapper;

    /**
     * 通过用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public PgUsers queryByName(String userName) {
        PgUsers pgUsersList = pgUsersMapper.queryByName(userName);
        if (pgUsersList != null) {

            return pgUsersList;
        }
        return null;
    }
}
