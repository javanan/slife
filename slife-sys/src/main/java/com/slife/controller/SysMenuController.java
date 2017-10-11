package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.enums.SysMenuType;
import com.slife.service.ISysMenuService;
import com.slife.service.impl.SysRoleService;
import com.slife.shiro.SlifeSysUser;
import com.slife.util.ReturnDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chen on 2017/7/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统菜单
 */
@Controller
@RequestMapping(value = "/sys/menu")
public class SysMenuController extends BaseController{

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private ISysMenuService sysMenuService;


    /**
     * 进入系统菜单管理首页
     */
    @GetMapping(value = "")
    public String list(Model model) {

        model.addAttribute("resourceTree", JSON.toJSONString(sysMenuService.getMenuTree()));
        model.addAttribute("resourceTypes", SysMenuType.values());

        return "menu/list";
    }

    /**
     * 查询系统用户 侧边栏菜单
     */
    @GetMapping(value = "/usersidemenu")
    @ResponseBody
    public ReturnDTO selectUserSideMenu() {

        return ReturnDTOUtil.success(sysMenuService.CaseMenu(SlifeSysUser.id()));
    }
}
