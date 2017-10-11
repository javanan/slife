package com.slife.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.slife.base.vo.JsTree;
import com.slife.dao.SysMenuDao;
import com.slife.entity.SysMenu;
import com.slife.service.ISysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2017/4/24.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: sys 菜单 servive
 */
@Service
@Transactional(readOnly = true)
public class SysMenuService extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {



    /**
     * 根据用户的id 或者该用户具有的菜单列表
     * @param userId
     * @return
     */
    public List<SysMenu> selectMenusByUserId(Long userId){
       return this.baseMapper.selectMenusByUserId(userId);
    }

    /**
     * 查询系统用户 侧边栏菜单
     * @param userId
     * @return
     */
    public List<SysMenu> CaseMenu(Long userId){
        Map<Long,List<SysMenu>> map= Maps.newHashMap();

        List<SysMenu> sysMenus=this.baseMapper.selectMenusByUserId(userId);

       for(SysMenu sysMenu : sysMenus){
            List<SysMenu> parentMenu=map.get(sysMenu.getParentId());
            if(parentMenu==null){
                parentMenu= Lists.newArrayList();
            }
           parentMenu.add(sysMenu);
           map.put(sysMenu.getParentId(),parentMenu);
        }
        List<SysMenu> retList= MakeMenu(map,0L);
        Collections.sort(retList);
        return retList;
    }


    public List<SysMenu> MakeMenu(Map<Long,List<SysMenu>> map,Long supId){
        List<SysMenu> sysMenus=Lists.newArrayList();
        List<SysMenu> menuList=map.get(supId);
        if(menuList!=null){
            for(SysMenu me : menuList){
                me.setChildren(MakeMenu(map,me.getId()));
                sysMenus.add(me);
            }
        }
        return sysMenus;
    }

    /**
     * 菜单管理 菜单树
     * @return
     */
    public List<JsTree> getMenuTree(){

        List<SysMenu> sysMenus=selectList(null);

        List<JsTree> res=Lists.newArrayList();
        for(SysMenu sysMenu : sysMenus){
            JsTree jt=new JsTree();
            jt.setId(sysMenu.getId().toString());
            jt.setParent(sysMenu.getParentId()==null?"#":(sysMenu.getParentId().compareTo(0L)>0?sysMenu.getParentId().toString
                    ():"#"));
            jt.setText(sysMenu.getName());
            jt.setIcon(sysMenu.getIcon());
            res.add(jt);
        }
        return res;
    }

}
