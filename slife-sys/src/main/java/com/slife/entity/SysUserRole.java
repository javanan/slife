package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by chen on 2017/9/1.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统用户关联表
 */
@TableName("sys_user_sys_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 5634076739726664860L;


    @TableField(value = "sys_user_id")
    private Long sysUserId;

    @TableField(value = "sys_role_id")
    private Long sysRoleId;

    public SysUserRole() {

    }
    public SysUserRole(Long sysUserId, Long sysRoleId) {
        this.sysUserId = sysUserId;
        this.sysRoleId = sysRoleId;
    }


    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

}
