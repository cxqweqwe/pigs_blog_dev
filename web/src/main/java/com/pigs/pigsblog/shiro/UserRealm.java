package com.pigs.pigsblog.shiro;

import com.pigs.pigsblog.entity.PgPermission;
import com.pigs.pigsblog.entity.PgRole;
import com.pigs.pigsblog.entity.PgUsers;
import com.pigs.pigsblog.mapper.PgUsersMapper;
import com.pigs.pigsblog.service.PgUsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/3/18 22:25
 * @effect
 */
public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);


    @Autowired
    private PgUsersMapper pgUsersMapper;

    @Autowired
    private PgUsersService pgUsersService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PgUsers pgUsers = (PgUsers) principalCollection.getPrimaryPrincipal();

        /**
         * 通过遍历，将user对象中的角色和权限都打包到info对象中
         */
        for (PgRole pgRole : pgUsers.getPgRoleList()) {
            /**
             * 将角色名存到角色中
             */
            authorizationInfo.addRole(pgRole.getRoleName());
            for (PgPermission p : pgRole.getPgPermissionList()) {
                /**
                 * 将数据表中的权限“user:add”存到info中
                 */
                authorizationInfo.addStringPermission(p.getPermissionPermission());
            }
        }
        return authorizationInfo;
    }


    /**
     * 认证信息
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();

        PgUsers pgUsers = pgUsersService.queryByName(userName);

        if (pgUsers == null) {
            throw new UnknownAccountException("不知道这个用户");
        }

        /**
         * 返回用户对象 密码 加密后的加盐 realm 名称
         */
        return new SimpleAuthenticationInfo(pgUsers, pgUsers.getUserPassword(), ByteSource.Util.bytes(pgUsers.getSalt()), getName());
    }
}
