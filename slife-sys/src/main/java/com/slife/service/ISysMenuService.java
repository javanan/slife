package com.slife.service;

import com.baomidou.mybatisplus.service.IService;
import com.slife.base.vo.JsTree;
import com.slife.entity.SysMenu;

import java.util.List;

/**
 *
 * @author chen
 * @date 2017/9/19
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public interface ISysMenuService extends IService<SysMenu> {
     List<SysMenu> selectMenusByUserId(Long userId);

     List<SysMenu> CaseMenu(Long userId);

     List<JsTree> getMenuTree();

    /**
     * 保存菜单
     * @param sysMenu
     */
     void add(SysMenu sysMenu);

    /**
     * 更新菜单
     * @param sysMenu
     */
    void update(SysMenu sysMenu);

    /**
     * 把菜单设置为失效
     * @param id
     */
    void disableMenu(Long id);
}
