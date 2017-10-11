package com.slife.vo;

import com.slife.entity.SysUser;

import java.util.List;

/**
 * Created by chen on 2017/7/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统用户 vo
 */
public class SysUserVO extends SysUser {


    private List<SysRoleVO> sysRoleVOs;

    public List<SysRoleVO> getSysRoleVOs() {
        return sysRoleVOs;
    }

    public void setSysRoleVOs(List<SysRoleVO> sysRoleVOs) {
        this.sysRoleVOs = sysRoleVOs;
    }
}
