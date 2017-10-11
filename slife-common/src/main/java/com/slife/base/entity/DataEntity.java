/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.slife.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slife.constant.Global;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 数据Entity类
 * @param <T>
 */
public abstract class DataEntity<T extends Model> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	@TableField(value = "create_id",validate= FieldStrategy.IGNORED)
	protected Long createId;	// 创建者
	@TableField(value = "create_date",validate=FieldStrategy.IGNORED)
	protected Date createDate;	// 创建日期
	@TableField(value = "update_id",validate=FieldStrategy.IGNORED)
	protected Long updateId;	// 更新者
	@TableField(value = "update_date",validate=FieldStrategy.IGNORED)
	protected Date updateDate;	// 更新日期
	@TableField(value = "del_flag")
	protected String delFlag; 	// 删除标记（Y：正常；N：删除；A：审核；）
	protected String remark;	// 备注



	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(Long insertUserId){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
/*		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}*/

			//setId(IdGen.uuid());

		if (null!=insertUserId){
			this.updateId = insertUserId;
			this.createId = insertUserId;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(Long updateUserId){

		if (null!=updateUserId){
			this.updateId = updateUserId;
		}
		this.updateDate = new Date();
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public DataEntity() {
		super();
		this.delFlag = Global.DEL_FLAG_NORMAL;

	}

	public DataEntity(Long id) {
		super(id);
	}

	
	/**
	 * 更新之前执行方法，需要手动调用
	 */

	
	@Length(min=0, max=500,message="备注信息长度必须介于 1 和 500 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
