package com.pigs.pigsblog.mapper;

import com.pigs.pigsblog.entity.PgUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@Repository
public interface PgUsersMapper extends BaseMapper<PgUsers> {

    /**
     * 通过用户名查询信息
     * @param userName
     * @return
     */
    public PgUsers queryByName(String userName);

}
