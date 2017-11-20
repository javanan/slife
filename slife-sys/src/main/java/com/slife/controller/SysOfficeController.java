package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.annotation.SLog;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.SysMenu;
import com.slife.entity.SysOffice;
import com.slife.entity.SysUser;
import com.slife.entity.SysUserOffice;
import com.slife.enums.SysMenuType;
import com.slife.service.ISysMenuService;
import com.slife.service.ISysOfficeService;
import com.slife.service.ISysUserOfficeService;
import com.slife.shiro.SlifeSysUser;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2017/11/13
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Controller
@RequestMapping(value = "/sys/office")
public class SysOfficeController extends BaseController {


    @Autowired
    private ISysOfficeService sysOfficeService;

    @Autowired
    private ISysUserOfficeService sysUserOfficeService;


    @SLog("获取组织用户列表数据")
    @ApiOperation(value = "获取组织用户列表数据", notes = "获取组织用户列表数据:使用约定的DataTable")
    @PostMapping(value = "/user/list")
    @ResponseBody
    public DataTable<SysUser> list(@RequestBody DataTable dt, ServletRequest request) {

        return sysUserOfficeService.userList(dt);

    }

    /**
     * 批量添加用户到 office
     *
     * @param officeId
     * @param userIds
     */
    @PostMapping("/addUsersToOffice")
    public void addUsersToOffice(@RequestParam(value = "officeId") Long officeId,
                                 @RequestParam(value = "userIds") Long[] userIds,
                                 @RequestParam(value = "major") String major) {

        sysUserOfficeService.addUsers(officeId, userIds, major);

    }


    /**
     * 移除组织的用户
     *
     * @param officeId
     * @param userIds
     */
    @PostMapping("/delUsersFromOffice")
    public void delUsersFromOffice(@RequestParam(value = "officeId") Long officeId,
                                   @RequestParam(value = "userIds") Long[] userIds) {

        sysUserOfficeService.removeUsers(officeId, userIds);

    }


    /**
     * 组织架构树
     */
    @GetMapping(value = "")
    public String list(Model model) {
        model.addAttribute("officeTree", JSON.toJSONString(sysOfficeService.getOfficeTree()));
        return "office/list";
    }


    /**
     * 获取机构详情
     *
     * @param id 机构ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("office", sysOfficeService.selectById(id));
        return map;
    }


    /**
     * 保存资源信息
     *
     * @param sysOffice
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "insert")
    public String save(@Valid SysOffice sysOffice, RedirectAttributes redirectAttributes) {
        if (ObjectUtils.isEmpty(sysOffice.getId())) {
            sysOfficeService.add(sysOffice);
        } else {
            sysOfficeService.update(sysOffice);
        }

        redirectAttributes.addFlashAttribute("message", "保存组织成功");
        return "redirect:/sys/office";
    }


    /**
     * 设置为不可用
     * @param id
     * @return
     */
/*    @PostMapping(value="disable/{id}")
    @ResponseBody
    public ReturnDTO disable(@PathVariable("id") Long id){
        sysOfficeService.disableOffice(id);
        return ReturnDTOUtil.success();
    }*/
}
