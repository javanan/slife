package com.slife.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.base.vo.JsTree;
import com.slife.base.vo.JsTreeState;
import com.slife.constant.Global;
import com.slife.dao.SysRoleDao;
import com.slife.entity.SysMenu;
import com.slife.entity.SysRole;
import com.slife.entity.SysRoleMenu;
import com.slife.entity.SysUserRole;
import com.slife.service.ISysMenuService;
import com.slife.service.ISysRoleMenuService;
import com.slife.service.ISysRoleService;
import com.slife.service.ISysUserRoleService;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen
 * @date 2017/4/24
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: sys 角色 服务
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysRoleService extends BaseService<SysRoleDao, SysRole>  implements ISysRoleService {

    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private  SysRoleDao sysRoleDao;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void tf() {
        logger.info("----------------------------------------------------------");
        System.out.println(sysRoleDao);
        System.out.println(AopUtils.isAopProxy(sysRoleDao));
        System.out.println(AopUtils.isCglibProxy(sysRoleDao));
        System.out.println(AopUtils.isJdkDynamicProxy(sysRoleDao));
        SysRole s=new SysRole();
        s.setName("ddddd");
        insert(s);
        double d=1/0;
    }

    /**
     * 获取角色选中的菜单树
     *
     * @param roleId
     * @return
     */
    @Override
    public List<JsTree> selectMenuTreeHasSelectDis(Long roleId,Boolean disable) {

        List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.selectList(Condition.create().eq("sys_role_id", roleId));

        Map<Long, Long> map = new HashMap<>();
        sysRoleMenus.stream().parallel().forEach(sysRoleMenu -> map.put(sysRoleMenu.getSysMenuId(), sysRoleMenu.getSysMenuId()));

        List<SysMenu> list =  sysMenuService.selectList(null);

        List<JsTree> jts = list.stream().parallel().map(sysMenu -> {
            JsTree jt = new JsTree();
            jt.setId(sysMenu.getId().toString());
            jt.setParent(sysMenu.getParentId() == null ? "#" : (sysMenu.getParentId().compareTo(0L) > 0 ? sysMenu.getParentId()
                    .toString() : "#"));
            jt.setText(sysMenu.getName());
            JsTreeState jtState = new JsTreeState();
            if (disable){
                jtState.setDisabled(disable);
            }

            //一级节点除了主页,都是未选中状态
            if ((sysMenu.getParentId() == null || 0L == sysMenu.getParentId()) && !"/index".equalsIgnoreCase(sysMenu.getHref())) {
                jtState.setSelected(false);
            } else {
                jtState.setSelected(map.get(sysMenu.getId()) != null);
            }
            jt.setState(jtState);
            return jt;
        }).collect(Collectors.toList());

        return jts;
    }

    /**
     * 获取用户的角色和菜单
     *
     * @param userId
     * @return
     */
    @Override
    public  List<SysRole> selectRoleByUserId(Long userId) {
        List<SysRole> sysRoleList=this.baseMapper.selectRoleByUserId(userId);
        return sysRoleList;
    }

    /**
     * 查询系统中，没有删除，并且是可以使用的角色
     *
     * @return
     */
    @Override
    public List<SysRole> ListSysRoleUseable() {
        return selectList(Condition.create().eq("del_flag", Global.DEL_FLAG_NORMAL).eq("useable", Global.YES));
    }

    /**
     * 分页查询角色列表
     *
     * @param searchParams
     * @param dt
     * @return
     */
    @Override
    public DataTable<SysRole> PageSysRole(Map<String, Object> searchParams, DataTable<SysRole> dt) {
        Condition cnd = Condition.create();

        Page<SysRole> rolePage = new Page<SysRole>(dt.getPageNumber(), dt.getPageSize());
        selectPage(rolePage, cnd);

        dt.setTotal(rolePage.getTotal());
        dt.setTotal(rolePage.getTotal());
        dt.setRows(rolePage.getRecords());
        return dt;
    }

    /**
     * 获取用户的角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> listSysRoleByUser(Long userId) {
        List<SysUserRole> sysUserRoles = sysUserRoleService.selectList(Condition.create().eq("sys_user_id", userId));

        List<Long> roleIds = sysUserRoles.stream().parallel().map(sysUserRole -> sysUserRole.getSysRoleId()).collect
                (Collectors.toList());
        List<SysRole> roles = new ArrayList<>();
        if (!ObjectUtils.isEmpty(roleIds)) {
            roles = selectList(Condition.create().in("id", roleIds).eq("del_flag", Global.DEL_FLAG_NORMAL).eq("useable",
                    Global.YES));
        }
        return roles;
    }

    /**
     * 保存用户的角色
     *
     * @param userId
     * @param ids
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertSysRole(Long userId, Long[] ids) {

        //删除现有的用户角色
        sysUserRoleService.delete(Condition.create().eq("sys_user_id", userId));
        if (null != ids && ids.length > 0) {
            List<SysUserRole> sysUserRoles = Arrays.stream(ids).parallel().map(roleId -> {
                return new SysUserRole(userId, roleId);
            }).collect(Collectors.toList());
            //保存用户角色
            sysUserRoleService.insertBatch(sysUserRoles);
        }
    }



    /**
     * 保存角色和对应的菜单
     *
     * @param sysRole
     * @param menuIds
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertSysRole(SysRole sysRole, Long[] menuIds) {

        insertOrUpdate(sysRole);
        Long roleId = sysRole.getId();
        //删除现有的 角色 菜单
        sysRoleMenuService.delete(Condition.create().eq("sys_role_id", roleId));
        if (null != menuIds && menuIds.length > 0) {
            List<SysRoleMenu> sysRoleMenu = Arrays.stream(menuIds).parallel().map(menuId -> {
                return new SysRoleMenu(menuId, roleId);
            }).collect(Collectors.toList());
            //角色关联的菜单
            sysRoleMenuService.insertBatch(sysRoleMenu);
        }
    }

    /**
     * 检测角色编码是否存在
     *
     * @param code
     * @param id
     * @return
     */
    @Override
    public Boolean checkRoleCode(String code, Long id) {
        SysRole sysRole = selectOne(Condition.create().eq("code", code));
        return sysRole == null || !id.equals(0L) && sysRole.getId().equals(id);
    }



}
