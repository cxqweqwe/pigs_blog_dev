package com.pigs.pigsblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

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
 * @since 2020-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PgArticles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博文ID
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 发表用户ID
     */
    private Long userId;

    /**
     * 博文标题
     */
    private String articleTitle;

    /**
     * 博文内容
     */
    private String articleContent;

    /**
     * 浏览量
     */
    private Long articleViews;

    /**
     * 评论总数
     */
    private Long articleCommentCount;

    /**
     * 发表时间
     */
    private String articleDate;

    /**
     * 访问次数
     */
    private Long articleLikeCount;

    /**
     * 状态
     */
    private Integer articleSate;

    /**
     * 标签模型
     */
    @TableField(exist = false)
    private List<PgLabels> pgLabelsList;

    /**
     * 分类模型
     */
    @TableField(exist = false)
    private List<PgSorts> pgSortsList;

    /**
     * 文章封面图模型
     */
    @TableField(exist = false)
    private PgArticlesImg pgArticlesImgList;

    /**
     * 用户模型
     */
    @TableField(exist = false)
    private PgUsers pgUsers;



}
