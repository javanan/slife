package com.slife.controller;

import com.slife.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chen on 2017/7/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 公司的 controller
 */
@Controller
@RequestMapping(value = "/api/syscompany")
public class SysCompanyController extends BaseController {

/*    @Autowired
    private SysCompanyService sysCompanyService;

    @GetMapping(value = "/select/{id}")
    @ResponseBody
    protected SysCompany selectById(@PathVariable("id") Long id) {
        logger.info("根据公司id= {} 查询 公司详情",id);
        return sysCompanyService.selectById(id);
    }*/
}
