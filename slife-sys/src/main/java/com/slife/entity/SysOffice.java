package com.slife.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.TreeEntity;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 *
 * @author chen
 * @date 2017/3/2
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 职位entity
 */
@TableName("sys_office")
public class SysOffice extends TreeEntity<SysOffice> {


    private static final long serialVersionUID = -6092918829825347960L;


    /**
     * varchar(64)NULL编码
     */
    private String code;


    /**
     * varchar(64)NULL所在公司id
     */
    private String sysCompanyId;

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

    @Length(min = 0, max = 64, message = "编码长度必须介于 1 和 64 之间")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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



}
