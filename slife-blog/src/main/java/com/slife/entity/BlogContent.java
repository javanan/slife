package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.BaseEntity;
import com.slife.base.entity.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author chen
 * @date 2017/11/20
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 博客
 */
@TableName("blog_content")
public class BlogContent extends DataEntity<BlogContent> {


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    /**
     * varchar(255) NULL标题
     */
    @NotBlank
    private String title;

    /**
     * text NULL内容
     */
    @NotBlank
    private String content;
    /**
     * varchar(16) NULL类型
     */
    private String type;
    /**
     * varchar(200) NULL标签
     */
    private String tags;

    /**
     * varchar(200)NULL分类
     */
    private String categories;

    /**
     * int(1)NULL开启评论
     */
    @TableField(value = "allow_comment")
    private Integer allowComment;

    /**
     * int(1)NULL状态
     */
    private Integer status;

    /**
     * varchar(100)NULL作者
     */
    private String author;

}
