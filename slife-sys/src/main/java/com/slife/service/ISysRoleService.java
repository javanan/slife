package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.base.vo.DataTable;
import com.slife.base.vo.JsTree;
import com.slife.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chen
 * @date 2017/10/30
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public interface ISysRoleService extends IBaseService<SysRole> {



     void tf() ;

    /**
     * 获取角色选中的菜单树
     *
     * @param roleId
     * @return
     */
     List<JsTree> selectMenuTreeHasSelectDis(Long roleId, Boolean disable) ;

    /**
     * 获取用户的角色和菜单
     *
     * @param userId
     * @return
     */
     List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 查询系统中，没有删除，并且是可以使用的角色
     *
     * @return
     */
     List<SysRole> ListSysRoleUseable() ;

    /**
     * 分页查询角色列表
     *
     * @param searchParams
     * @param dt
     * @return
     */
     DataTable<SysRole> PageSysRole(Map<String, Object> searchParams, DataTable<SysRole> dt);

    /**
     * 获取用户的角色列表
     *
     * @param userId
     * @return
     */
     List<SysRole> listSysRoleByUser(Long userId);

    /**
     * 保存用户的角色
     *
     * @param userId
     * @param ids
     */
     void insertSysRole(Long userId, Long[] ids) ;



    /**
     * 保存角色和对应的菜单
     *
     * @param sysRole
     * @param menuIds
     */
     void insertSysRole(SysRole sysRole, Long[] menuIds);

    /**
     * 检测角色编码是否存在
     *
     * @param code
     * @param id
     * @return
     */
     Boolean checkRoleCode(String code, Long id) ;

}
