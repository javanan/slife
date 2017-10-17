package com.slife.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.ArrayUtils;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.constant.Global;
import com.slife.dao.SysRoleDao;
import com.slife.entity.SysRole;
import com.slife.entity.SysUserRole;
import com.slife.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by chen on 2017/4/24.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: sys 角色 服务
 */
@Service
@Transactional(readOnly = true)
public class SysRoleService extends BaseService<SysRoleDao, SysRole> {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 获取用户的角色和菜单
     *
     * @param userId
     * @return
     */
    public List<SysRoleVO> selectRoleByUserId(Long userId) {
        return this.baseMapper.selectRoleByUserId(userId);
    }

    /**
     * 查询系统中，没有删除，并且是可以使用的角色
     *
     * @return
     */
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
    @Transactional(readOnly = false)
    public void insertSysRole(Long userId, Long[] ids) {

        //删除现有的用户角色
        sysUserRoleService.delete(Condition.create().eq("sys_user_id", userId));
        if (null != ids && ids.length > 0) {

            List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
            for (Long roleId : ids) {
                sysUserRoles.add(new SysUserRole(userId, roleId));
            }
            //保存用户角色
            sysUserRoleService.insertBatch(sysUserRoles);
        }
    }
}
