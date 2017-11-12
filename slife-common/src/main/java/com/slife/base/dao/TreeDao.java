package com.slife.base.dao;




import com.slife.base.entity.TreeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chen on 2017/4/10.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:树型结构的DAO支持类实现
 */
@Component
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
     List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
     int updateParentIds(T entity);

}