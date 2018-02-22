package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.vo.PCAjaxVO;
import com.slife.service.ISysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2017/8/9.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统的数据字典
 */
@Controller
@RequestMapping(value = "/sys/dict")
public class SysDictController extends BaseController {
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 进入系统字典的主页
     *
     * @return
     */
    @RequiresPermissions("sys:dict:list")
    @GetMapping(value = "")
    public String list(Model model) {
        model.addAttribute("dictTrees", JSON.toJSON(sysDictService.getDictTree()).toString());
        return "dict/list";
    }



    /**
     * 保存数据
     *
     * @param dicKey;//字典名称
     * @param dicValue;//字典值
     * @param dicPid;//上级ID
     * @param desc;//字典描述
     * @param sort;//排序
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/insert")
    public String insert(Long id, String dicKey, String dicValue, Long dicPid, String type, String desc,
                         String sort, String path, String invalid, RedirectAttributes redirectAttributes) {

        if (null != id) {
            logger.info("更新字典 id={}", id);
            boolean success = sysDictService.update(id, dicKey, dicValue, type, desc, sort, invalid);
            redirectAttributes.addFlashAttribute("message", "更新数据字典成功");

        } else {
            sysDictService.insert(dicKey, dicValue, dicPid, type, desc, sort, invalid, path);
            redirectAttributes.addFlashAttribute("message", "保存数据字典成功");
        }

        return "redirect:/sys/dict";
    }

    /**
     * 获取资源
     *
     * @param id 资源ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("dict", sysDictService.selectById(id));
        return map;
    }

    /**
     * 删除节点已经子节点
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public PCAjaxVO delete(@PathVariable("id") Long id) {
        return sysDictService.delete(id);
    }
}
