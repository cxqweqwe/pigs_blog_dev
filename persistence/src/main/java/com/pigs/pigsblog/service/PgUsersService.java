package com.pigs.pigsblog.service;

import com.pigs.pigsblog.entity.PgUsers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
public interface PgUsersService extends IService<PgUsers> {

    /**
     * 通过用户名查询信息
     * @param userName
     * @return
     */
    public PgUsers queryByName(String userName);

}
