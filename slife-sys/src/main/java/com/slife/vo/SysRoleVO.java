package com.slife.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/7/17.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class SysRoleVO implements Serializable {
    /**
     * 实体编号（唯一标识）
     */

    protected Long id;
    /**
     * varchar(64) NOT NULL角色名称
     */
    private String name;
    /**
     * varchar(64) NULL英文名称
     */
    private String code;
    /**
     * 菜单列表
     */
    private List<SysMenuVO> sysMenuVOs;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SysMenuVO> getSysMenuVOs() {
        return sysMenuVOs;
    }

    public void setSysMenuVOs(List<SysMenuVO> sysMenuVOs) {
        this.sysMenuVOs = sysMenuVOs;
    }

    public List<String> getPermissionList() {
        List<String> permiss = new ArrayList<String>();
        if (getSysMenuVOs() != null && getSysMenuVOs().size() > 0) {
            for (SysMenuVO sysMenuVO : getSysMenuVOs()) {
                permiss.add(sysMenuVO.getPermission());
            }
        }
        return permiss;
    }
}
