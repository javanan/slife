package com.slife.base.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.validator.constraints.Length;

/**
 * 数据Entity类
 *
 * @author chenjianann
 *
 * @version 2014-05-16
 */
public abstract class TreeEntity<T extends Model> extends DataEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * varchar(64) NULL父id
     */
    @TableField(value = "parent_id")
    protected Long parentId;
    /**
     * varchar(1000) NULL路径
     */
    protected String path;
    /**
     * int(11) NULL排序
     */
    protected Integer sort;


    /**
     * varchar(100)NULL图标
     */
    protected String icon;


    protected String name;
    @Length(min = 0, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 1000, message = "icon长度必须介于 1 和 1000 之间")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public TreeEntity() {
        super();
        this.sort = 30;
    }

    public TreeEntity(Long id) {
        super(id);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Length( max = 1000, message = "路径长度必须介于 1 和 1000 之间")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


}
