package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.TreeEntity;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * Created by chen on 2017/4/10.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统数据字典 实体类
 */
@TableName("sys_dict")
public class SysDict extends TreeEntity<SysDict> {

    private String jkey; //varchar(64) NULLkey
    private String jvalue; //varchar(1000) NULLvalue

    private String type;

    @TableField(exist = false)
    protected String name;

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 是否有效 Y 表示是   N表示否
     */
    private String invalid;

    /**
     * varchar(64)NULL所在公司id
     */
    @TableField(value = "sys_company_id")
    private String sysCompanyId;

    @Length(min = 0, max = 64, message = "key长度必须介于 1 和 64 之间")
    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }

    @Length(min = 0, max = 1000, message = "value长度必须介于 1 和 1000 之间")
    public String getJvalue() {
        return jvalue;
    }

    public void setJvalue(String jvalue) {
        this.jvalue = jvalue;
    }

    @Length(min = 0, max = 64, message = "公司id长度必须介于 1 和 64 之间")
    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }


    @Length(min = 0, max = 1, message = "类型长度为1")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Length(min = 0, max = 1, message = "是否启动长度为1")
    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}
