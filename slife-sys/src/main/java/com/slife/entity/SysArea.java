package com.slife.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by chen on 2017/3/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 区域信息表。
 */
@TableName("sys_area")
public class SysArea extends BaseEntity<SysArea> {

    /**
     * varchar(64) NULL名称
     */
    private String name;
    /**
     * int(11) NULL排序
     */
    private Integer sort;
    /**
     * varchar(64) NULLdkey
     */
    private String jkey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }



    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
