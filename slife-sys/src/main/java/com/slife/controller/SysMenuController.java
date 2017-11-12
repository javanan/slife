package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.entity.SysMenu;
import com.slife.enums.SysMenuType;
import com.slife.service.ISysMenuService;
import com.slife.shiro.SlifeSysUser;
import com.slife.util.ReturnDTOUtil;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    private ISysMenuService sysMenuService;


    /**
     * 获取菜单详情
     *
     * @param id 菜单ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("menu", sysMenuService.selectById(id));
        return map;
    }

    /**
     * 进入系统菜单管理首页
     */
    @GetMapping(value = "")
    public String list(Model model) {

        model.addAttribute("menuTree", JSON.toJSONString(sysMenuService.getMenuTree()));
        model.addAttribute("menuTypes", SysMenuType.values());

        System.out.println(this);
        System.out.println(AopUtils.isAopProxy(this));
        System.out.println(AopUtils.isCglibProxy(this));
        System.out.println(AopUtils.isJdkDynamicProxy(this));

        System.out.println(sysMenuService);
        System.out.println(AopUtils.isAopProxy(sysMenuService));
        System.out.println(AopUtils.isCglibProxy(sysMenuService));
        System.out.println(AopUtils.isJdkDynamicProxy(sysMenuService));
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

    /**
     * 保存资源信息
     *
     * @param sysMenu
     * @param redirectAttributes
     *
     * @return
     */
    @PostMapping(value="insert")
    public String save(@Valid SysMenu sysMenu, RedirectAttributes redirectAttributes){
        if (ObjectUtils.isEmpty(sysMenu.getId())) {
            sysMenuService.add(sysMenu);
        }else {
            sysMenuService.update(sysMenu);
        }

        redirectAttributes.addFlashAttribute("message","保存菜单成功");
        return "redirect:/sys/menu";
    }


    /**
     * 设置为不可用
     * @param id
     * @return
     */
    @PostMapping(value="disable/{id}")
    @ResponseBody
    public ReturnDTO disable(@PathVariable("id") Long id){
        sysMenuService.disableMenu(id);
        return ReturnDTOUtil.success();
    }
}
