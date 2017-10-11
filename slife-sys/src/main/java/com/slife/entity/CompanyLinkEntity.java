package com.slife.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.slife.base.entity.DataEntity;
import com.slife.constant.Global;
import org.hibernate.validator.constraints.Length;

/**
 * Created by chen on 2017/7/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 和 公司 表 管理的 entity
 */
public abstract class CompanyLinkEntity<T extends Model> extends DataEntity<T> {


    public CompanyLinkEntity() {
        super();
        this.delFlag = Global.DEL_FLAG_NORMAL;

    }


    public CompanyLinkEntity(Long id) {
        super(id);
    }

    /**
     *
     * 公司实体对象
     */
    @TableField(exist=false)
    protected SysCompany sysCompany;

    /**
     * 公司iD
     */
   // @Length(min = 0, max = 64, message = "公司id长度必须介于 1 和 64 之间")
    @TableField(value = "sys_company_id")
    protected Long sysCompanyId;

    public SysCompany getSysCompany() {
        return sysCompany;
    }

    public void setSysCompany(SysCompany sysCompany) {
        this.sysCompany = sysCompany;
    }

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }
}
