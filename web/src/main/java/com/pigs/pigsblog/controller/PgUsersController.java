package com.pigs.pigsblog.controller;


import com.pigs.pigsblog.entity.PgUsers;
import com.pigs.pigsblog.model.ResultFormat;
import com.pigs.pigsblog.service.PgUsersService;
import com.pigs.pigsblog.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author PIGS
 * @since 2020-04-15
 * <p>
 * 用户前端控制器
 */
@RestController
@RequestMapping("/pgUsers/")
@Api(value = "/pgUsers", description = "用户前端控制器")
public class PgUsersController {

    private Logger logger = LoggerFactory.getLogger(PgUsersController.class);

    @Autowired
    private PgUsersService pgUsersService;

    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public ResultFormat pgUsersLogin(@RequestBody PgUsers pgUsers) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(pgUsers.getUserName(), pgUsers.getUserPassword());
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                logger.info("登录成功");
                return ResultUtil.success();
            }
        } catch (UnknownAccountException e) {
            logger.info("账号不存在：");
            return ResultUtil.error(404, "账号不存在");
        } catch (IncorrectCredentialsException ice) {
            logger.info("密码错误：");
            return ResultUtil.error(100, "密码输入错误");
        } catch (Exception e) {
            logger.info("发生错误={}", e.getMessage());
        }

        return ResultUtil.error(500, "稍后重试！");
    }

}
