package com.slife.base.vo;

import java.io.Serializable;

/**
 * Created by chen on 2017/7/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class BaseVO implements Serializable{

    private static final long serialVersionUID = -989209809168644953L;
    /**
     * 记录id
     */
    protected Long id;
    /**
     * 备注
     */
    protected String remark;

    /**
     * 删除标记
     */
    protected String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
