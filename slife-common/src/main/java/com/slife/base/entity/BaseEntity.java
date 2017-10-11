/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.slife.base.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
 * Entity支持类
 *
 * @param <T>
 */

public abstract class BaseEntity<T extends Model> extends Model<T>  {



    /**
     * 实体编号（唯一标识）
     */

    protected Long id;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
   /* protected Map<String, String> sqlMap;*/

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
  /*  protected boolean isNewRecord = false;*/

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     *
     * @return
     */
/*    public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }
    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }*/
    public BaseEntity() {

    }

    public BaseEntity(Long id) {
        this();
        this.id = id;
    }
    @JsonSerialize(using=ToStringSerializer.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  /*  @JsonIgnore
    @XmlTransient
    public Map<String, String> getSqlMap() {
        if (sqlMap == null) {
            sqlMap = Maps.newHashMap();
        }
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }*/

    /**
     * 插入之前执行方法，子类实现
     */
    public abstract void preInsert(Long insertUserId);

    /**
     * 更新之前执行方法，子类实现
     */
    public abstract void preUpdate(Long updateUserId);

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */


    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }




}
