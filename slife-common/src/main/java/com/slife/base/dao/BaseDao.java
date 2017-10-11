package com.slife.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Created by chen on 2017/4/10.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:   DAO支持类实现
 */


public interface BaseDao<T> extends BaseMapper<T> {

     void insert(String tableName) ;
}
