package com.slife.controller;

import com.slife.annotation.SLog;
import com.slife.base.controller.BaseController;
import com.slife.base.vo.DataTable;
import com.slife.entity.SysRole;
import com.slife.entity.SysUser;
import com.slife.service.impl.SysRoleService;
import com.slife.util.ServletUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    private SysRoleService sysRoleService;

    /**
     * 进入系统角色列表
     *
     * @param model
     * @return
     */
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath()+"/sys/role/");
        return "role/list";
    }



    @SLog("获角色列表")
    @ApiOperation(value = "获角色列表", notes = "获角色列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SysRole> list(@RequestBody DataTable dt, ServletRequest request) {
        return sysRoleService.pageSearch(dt);
    }
}
