package com.slife.dao;


import com.slife.base.dao.CrudDao;
import com.slife.entity.SysRole;
import com.slife.vo.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chen on 2017/3/2.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 角色 dao接口
 */
public interface SysRoleDao extends CrudDao<SysRole> {


    /**
     * 获取某个用户的角色和菜单
     *
     * @param userId
     * @return
     */
    List<SysRoleVO> selectRoleByUserId(Long userId);

}
