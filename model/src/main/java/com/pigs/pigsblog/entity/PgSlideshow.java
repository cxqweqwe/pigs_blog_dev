package com.pigs.pigsblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PgSlideshow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图id
     */
    @TableId(value = "slideshow_id", type = IdType.AUTO)
    private Integer slideshowId;

    /**
     * 轮播图名称
     */
    private String slideshowName;

    /**
     * 轮播图图片
     */
    private String slideshowImg;

    /**
     * 轮播图创建时间
     */
    private String slideshowTime;


}
