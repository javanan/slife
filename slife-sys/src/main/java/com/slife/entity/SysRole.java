package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slife.constant.Global;
import com.slife.enums.DataScopeEnum;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianan on 2017/3/1-16:12.
 * <p>
 * Describe: 角色实体类DO
 */
@TableName("sys_role")
public class SysRole extends CompanyLinkEntity<SysRole> {

    /**
     * varchar(64) NOT NULL角色名称
     */
    private String name;
    /**
     * varchar(64) NULL英文名称
     */
    private String code;

    /**
     * varchar(64) NULL数据范围
     */
    @TableField(value = "data_scope")
    private String dataScope;

    /**
     * 菜单列表
     */
    @TableField(exist = false)
    private List<SysMenu> sysMenus;

    /**
     * varchar(64) NULL是否可用
     */
    private String useable;

    @TableField(exist = false)
    private SysCompany sysCompany;    // 归属公司

    @TableField(exist = false)
    private SysUser sysUser;        // 根据用户ID查询角色列表


    @TableField(exist = false)
    private List<SysMenu> sysMenuList = new ArrayList(); // 拥有菜单列表

    @TableField(exist = false)
    private List<SysOffice> sysOfficeList = new ArrayList(); // 按明细设置数据范围



    public List<SysMenu> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<SysMenu> sysMenus) {
        this.sysMenus = sysMenus;
    }

    @Override
    @JsonIgnore
    public SysCompany getSysCompany() {
        return sysCompany;
    }

    @Override
    public void setSysCompany(SysCompany sysCompany) {
        this.sysCompany = sysCompany;
    }



    @Length(min = 0, max = 64, message = "角色code长度必须介于 1 和 64 之间")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }




    public SysRole() {
        super();
        this.dataScope = DataScopeEnum.DATA_SCOPE_SELF.name();
        this.useable = Global.YES;
    }

    public SysRole(Long id) {
        super(id);
    }

    public SysRole(SysUser sysUser) {
        this();
        this.sysUser = sysUser;
    }

    @Length(min = 0, max = 64, message = "角色名称长度必须介于 1 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }


    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }


    @JsonIgnore
    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @JsonIgnore
    public List<SysMenu> getSysMenuList() {
        return sysMenuList;
    }

    public void setSysMenuList(List<SysMenu> sysMenuList) {
        this.sysMenuList = sysMenuList;
    }

    @JsonIgnore
    public List<SysOffice> getSysOfficeList() {
        return sysOfficeList;
    }

    public void setSysOfficeList(List<SysOffice> sysOfficeList) {
        this.sysOfficeList = sysOfficeList;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
