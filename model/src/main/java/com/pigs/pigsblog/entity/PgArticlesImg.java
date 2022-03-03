package com.pigs.pigsblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author PIGS
 * @since 2020-04-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PgArticlesImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章封面图id
     */
    @TableId(value = "img_id", type = IdType.AUTO)
    private Integer imgId;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 文章封面图路径
     */
    private String imgUrl;

    /**
     * 文章封面图状态
     */
    private Integer imgState;


}
