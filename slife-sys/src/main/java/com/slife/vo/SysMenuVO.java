package com.slife.vo;

import java.io.Serializable;

/**
 * Created by chen on 2017/7/17.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class SysMenuVO implements Serializable{
    /**
     * 实体编号（唯一标识）
     */

    protected Long id;
    /**
     * varchar(100) 名称
     */
    protected String name;
    /**
     * varchar(100) NULL权限标识
     */
    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
