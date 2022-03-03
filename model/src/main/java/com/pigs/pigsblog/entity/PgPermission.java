package com.pigs.pigsblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author PIGS
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PgPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限描述
     */
    private String permissionDescribe;

    /**
     * 权限
     */
    private String permissionPermission;

    /**
     * 权限url
     */
    private String permissionUrl;

    /**
     * 权限创建时间
     */
    private String permissionCreateTime;

    /**
     * 权限更新时间
     */
    private String permissionUpdateTime;

    /**
     * 权限状态
     */
    private Integer permissionState;


}
