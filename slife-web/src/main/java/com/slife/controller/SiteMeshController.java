package com.slife.controller;

import com.slife.base.controller.BaseController;
import com.slife.service.ISysMenuService;
import com.slife.shiro.SlifeSysUser;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chen on 2017/7/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:SiteMesh 操作
 */
@Controller
public class SiteMeshController extends BaseController{

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询系统用户 侧边栏菜单
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "layouts")
    public String getIndex(Model model, HttpServletRequest request) {

        model.addAttribute("slife", SlifeSysUser.ShiroUser());
        model.addAttribute("menus", sysMenuService.CaseMenu(SlifeSysUser.id()));

        return "layouts/default";
    }

    @PostMapping(value = "layouts")
    public String postIndex(Model model, HttpServletRequest request) {
        return getIndex( model,  request);
    }
}
