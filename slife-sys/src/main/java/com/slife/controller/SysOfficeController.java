package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.entity.SysMenu;
import com.slife.entity.SysOffice;
import com.slife.enums.SysMenuType;
import com.slife.service.ISysMenuService;
import com.slife.service.ISysOfficeService;
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
 *
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
     *
     * @return
     */
    @PostMapping(value="insert")
    public String save(@Valid SysOffice sysOffice, RedirectAttributes redirectAttributes){
        if (ObjectUtils.isEmpty(sysOffice.getId())) {
            sysOfficeService.add(sysOffice);
        }else {
            sysOfficeService.update(sysOffice);
        }

        redirectAttributes.addFlashAttribute("message","保存组织成功");
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
