package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import com.slife.base.entity.TreeEntity;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/3/2.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统菜单，也即是资源，角色的权限将对应相应的资源菜单
 */
@TableName("sys_menu")
public class SysMenu extends TreeEntity<SysMenu> implements Comparable<SysMenu> {


    /**
     * varchar(10) NULL菜单类型，按钮或者menu
     */
    private String type;
    /**
     * varchar(1000) NULL链接地址
     */
    private String href;
    /**
     * varchar(10) NULL打开方式
     */
    private String target;


    /**
     * char(1) NULL是否展示
     */
    @TableField(value ="show_flag")
    private String showFlag;
    /**
     * varchar(100) NULL权限标识
     */
    private String permission;

    /**
     * varchar(64)NULL所在公司id
     */
    @TableField(value ="sys_company_id")
    private String sysCompanyId;

    @TableField(exist=false)
    public List<SysMenu> children= new ArrayList();

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    /**
     *
     * 公司实体对象
     */
    @TableField(exist=false)
    protected SysCompany sysCompany;

    public SysCompany getSysCompany() {
        return sysCompany;
    }

    public void setSysCompany(SysCompany sysCompany) {
        this.sysCompany = sysCompany;
    }

    @Length(min = 0, max = 10, message = "菜单类型长度必须介于 1 和 10 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 1000, message = "路径长度必须介于 1 和 1000 之间")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Length(min = 0, max = 10, message = "打开方式长度必须介于 1 和 10 之间")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    @Length(min = 0, max = 1, message = "标识长度必须介于 1 和 1 之间")
    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    @Length(min = 0, max = 100, message = "权限长度必须介于 1 和 100 之间")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    @Length(min = 0, max = 64, message = "公司id长度必须介于 1 和 64 之间")
    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public int compareTo(SysMenu o) {
        return this.getSort().compareTo(o.getSort());
    }

    /**
     * varchar(100) 名称
     */
    protected String name;
    @Length(min = 0, max = 100, message = "资源名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
