package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author chen
 * @date 2017/10/26
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 角色 菜单 表
 */
@TableName("sys_role_sys_menu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -3876781931176875589L;

    public SysRoleMenu() {

    }

    public SysRoleMenu(Long sysMenuId, Long sysRoleId) {
        this.sysMenuId = sysMenuId;
        this.sysRoleId = sysRoleId;
    }

    @TableField(value = "sys_menu_id")
    private Long sysMenuId;

    @TableField(value = "sys_role_id")
    private Long sysRoleId;

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }
}
