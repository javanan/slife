package com.slife.service.impl;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.slife.base.vo.JsTree;
import com.slife.constant.Global;
import com.slife.dao.SysMenuDao;
import com.slife.entity.SysMenu;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.ISysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author chen
 * @date 2017/4/24
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: sys 菜单 servive
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysMenuService extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {


    /**
     * 把菜单设置为失效
     *
     * @param id
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void disableMenu(Long id) {
        SysMenu sysMenu = selectById(id);
        Optional.ofNullable(sysMenu).orElseThrow(() -> new SlifeException(HttpCodeEnum.NOT_FOUND));

        List<SysMenu> delList = selectList(Condition.create().like("path", sysMenu.getPath(), SqlLike.RIGHT));
        delList.stream().parallel().forEach(menu -> menu.setShowFlag(Global.NO));
        updateBatchById(delList);
        //TODO 判断是否有角色，有角色要清理角色与资源关系

    }

    /**
     * 新增菜单
     *
     * @param sysMenu
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void add(SysMenu sysMenu) {

        insert(sysMenu);
        if (Global.TOP_TREE_NODE.equals(sysMenu.getParentId())) {
            sysMenu.setPath(sysMenu.getId() + ".");
        } else {

            sysMenu.setPath(sysMenu.getPath() + sysMenu.getId() + ".");
        }
        updateById(sysMenu);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void update(SysMenu sysMenu) {

        updateById(sysMenu);
    }


    /**
     * 根据用户的id 或者该用户具有的菜单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> selectMenusByUserId(Long userId) {
        return this.baseMapper.selectMenusByUserId(userId);
    }

    /**
     * 查询系统用户 侧边栏菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> CaseMenu(Long userId) {
        Map<Long, List<SysMenu>> map = Maps.newHashMap();

        List<SysMenu> sysMenus = this.baseMapper.selectMenusByUserId(userId);

        for (SysMenu sysMenu : sysMenus) {
            List<SysMenu> parentMenu = map.get(sysMenu.getParentId());
            if (parentMenu == null) {
                parentMenu = Lists.newArrayList();
            }
            parentMenu.add(sysMenu);
            map.put(sysMenu.getParentId(), parentMenu);
        }
        List<SysMenu> retList = MakeMenu(map, 0L);
        Collections.sort(retList);
        return retList;
    }


    public List<SysMenu> MakeMenu(Map<Long, List<SysMenu>> map, Long supId) {
        List<SysMenu> sysMenus = Lists.newArrayList();
        List<SysMenu> menuList = map.get(supId);
        if (menuList != null) {
            for (SysMenu me : menuList) {
                me.setChildren(MakeMenu(map, me.getId()));
                sysMenus.add(me);
            }
        }
        return sysMenus;
    }

    /**
     * 菜单管理 菜单树
     *
     * @return
     */
    @Override
    public List<JsTree> getMenuTree() {

        List<SysMenu> sysMenus = selectList(null);

        List<JsTree> res = Lists.newArrayList();
        for (SysMenu sysMenu : sysMenus) {
            JsTree jt = new JsTree();
            jt.setId(sysMenu.getId().toString());
            jt.setParent(sysMenu.getParentId() == null ? "#" : (sysMenu.getParentId().compareTo(0L) > 0 ? sysMenu.getParentId().toString
                    () : "#"));
            jt.setText(sysMenu.getName());
            jt.setIcon(sysMenu.getIcon());
            res.add(jt);
        }
        return res;
    }


}
