package com.slife.service;

import com.baomidou.mybatisplus.service.IService;
import com.slife.base.vo.JsTree;
import com.slife.entity.SysMenu;

import java.util.List;

/**
 * Created by chen on 2017/9/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public interface ISysMenuService extends IService<SysMenu> {
     List<SysMenu> selectMenusByUserId(Long userId);

     List<SysMenu> CaseMenu(Long userId);

     List<JsTree> getMenuTree();
}
