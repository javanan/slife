package com.slife.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.SysRoleDao;
import com.slife.entity.SysRole;
import com.slife.vo.SysRoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        return selectList(Condition.create().eq("del_flag", "Y").eq("useable", "Y"));
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
     * 获取用户的角色
     * @param userId
     * @return
     */
    public List<SysRoleVO> ListSysRoleUseable(Long userId) {
        return null;
    }
}
