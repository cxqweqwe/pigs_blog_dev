package com.pigs.pigsblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.pigs.pigsblog.util.PhoneNumBer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
/**
 * <p>
 * 
 * </p>
 * 用户模型
 * @author PIGS
 * @since 2020-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户模型")
public class PgUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户id",required = true)
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户IP
     */
    @ApiModelProperty(value = "用户ip",required = true)
    @NotNull(message = "IP不能为空！")
    private String userIp;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    @NotNull(message = "用户名不能为空！")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码",required = true)
    @NotNull(message = "密码不能为空！")
    private String userPassword;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱",required = true)
    @Email
    private String userEmail;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像",required = true)
    @NotNull(message = "头像不能为空！")
    private String userProfilePhoto;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "用户注册时间",required = true)
    @NotNull(message = "注册不能为空！")
    private String userRegistrationTime;

    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日",required = true)
    @NotNull(message = "生日不能为空！")
    private String userBirthday;

    /**
     * 用户年龄
     */
    @ApiModelProperty(value = "用户年龄",required = true)
    @NotNull(message = "年龄不能为空！")
    private Integer userAge;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号码",required = true)
    @NotNull(message = "手机号码不能为空！")
    @PhoneNumBer
    private String userTelephoneNumber;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称",required = true)
    @NotNull(message = "昵称不能为空！")
    private String userNickname;

    /**
     * 盐
     */
    @ApiModelProperty(value = "用户盐",required = true)
    @NotNull(message = "盐不能为空！")
    private String salt;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态",required = true)
    @NotNull(message = "状态不能为空！")
    private String userState;

    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别",required = true)
    @NotNull(message = "性别不能为空！")
    private Integer userSex;

    /**
     * 角色模型
     */
    @TableField(exist = false)
    private List<PgRole> pgRoleList;

    /**
     * 博文模型
     */
    @TableField(exist = false)
    private List<PgArticles> pgArticlesList;


}
