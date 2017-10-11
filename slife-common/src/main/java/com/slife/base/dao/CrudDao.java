package com.slife.base.dao;

import java.util.List;

/**
 * Created by chen on 2017/4/10.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * DAO支持类实现
 *
 * @param <T>
 */

public interface CrudDao<T> extends BaseDao<T>  {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    T getById(String id);

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    T getByEntity(T entity);

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     *
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 查询所有数据列表
     *
     * @param entity
     * @return
     */
    List<T> findAllList(T entity);

    /**
     * 查询所有数据列表
     *
     * @return
     * @see public List<T> findAllList(T entity)
     */
    List<T> findAllList();


    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 批量更新数据
     * @param ids
     * @return
     */
    int update(String[] ids);

    /**
     * 根据id更新
     *
     * @param id
     * @return
     */
    int updateById(String id);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     *
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 批量删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @param ids
     * @return
     */
    int delete(String[] ids);

    /**
     * 物理删除
     *
     * @param entity
     * @return
     */
    int remove(T entity);

    /**
     * 批量物理删除
     * @param ids
     * @return
     */
    int remove(String[] ids);

}
