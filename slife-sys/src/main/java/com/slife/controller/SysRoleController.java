package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.annotation.SLog;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.SysRole;
import com.slife.service.ISysMenuService;
import com.slife.service.ISysRoleService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by chen on 2017/9/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统角色控制器
 */
@Controller
@RequestMapping(value = "/sys/role")
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuService sysMenuService;



    /**
     * 进入系统角色列表
     *
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:list")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath() + "/sys/role/");
        return "role/list";
    }


    @SLog("获角色列表")
    @ApiOperation(value = "获角色列表", notes = "获角色列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SysRole> list(@RequestBody DataTable dt, ServletRequest request) {
        return sysRoleService.pageSearch(dt);
    }


    /**
     * 进入创建角色界面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/insert")
    public String create(Model model, HttpServletRequest request) {
        SysRole sysRole = new SysRole();
        sysRole.setId(0L);
        model.addAttribute("action", "insert");
        model.addAttribute("role", sysRole);
        model.addAttribute("url", request.getContextPath() + "/sys/role/");
        model.addAttribute("menuTree", JSON.toJSONString(sysMenuService.getMenuTree()));
        return "role/detail";
    }

    @ApiOperation(value = "创建角色操作", notes = "创建角色操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid SysRole sysRole, @RequestParam(value = "ids", defaultValue = "") Long[] menuIds) {
        sysRole.setId(null);
        sysRoleService.insertSysRole(sysRole, menuIds);
        return ReturnDTOUtil.success();
    }

    /**
     * 检测角色编码是否存在
     *
     * @param id
     * @param code
     * @return
     */
    @GetMapping(value = "check/{id}")
    @ResponseBody
    public Boolean check(@PathVariable("id") Long id, @RequestParam("code") String code) {
        return sysRoleService.checkRoleCode(code, id);
    }

    /**
     * 查看用户详情界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        model.addAttribute("action", "detail");
        SysRole sysRole = sysRoleService.selectById(id);
        model.addAttribute("role", sysRole);
        model.addAttribute("menuTree", JSON.toJSONString(sysRoleService.selectMenuTreeHasSelectDis(id,true)));
        model.addAttribute("url", request.getContextPath() + "/sys/role/");
        return "role/detail";
    }

    /**
     * 更新角色界面
     *
     * @param id
     *
     * @return
     */

    @GetMapping(value="update/{id}")
    public String updateForm(@PathVariable("id") Long id,Model model, HttpServletRequest request){
        model.addAttribute("action","update");
        SysRole sysRole = sysRoleService.selectById(id);
        model.addAttribute("role",sysRole);
        model.addAttribute("url", request.getContextPath() + "/sys/role/");
        model.addAttribute("menuTree", JSON.toJSONString(sysRoleService.selectMenuTreeHasSelectDis(id,false)));
        return "role/detail";
    }


    @ApiOperation(value = "修改角色操作", notes = "修改角色操作")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid SysRole sysRole, @RequestParam(value = "ids", defaultValue = "") Long[] menuIds) {
        sysRoleService.insertSysRole(sysRole, menuIds);
        return ReturnDTOUtil.success();
    }


}
