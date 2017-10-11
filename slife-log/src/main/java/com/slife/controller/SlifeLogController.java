package com.slife.controller;

import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.SlifeLog;
import com.slife.service.ISlifeLogService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chen on 2017/9/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: slife 日志 controller
 */
@Controller
@RequestMapping("/monitor/log")
public class SlifeLogController extends BaseController {

    @Autowired
    private ISlifeLogService slifeLogService;


    @ApiOperation(value = "进入日志列表", notes = "进入日志列表")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath()+"/monitor/log/");
        return "log/list";
    }

    @ApiOperation(value = "获取日志列表数据", notes = "获取日志列表数据:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SlifeLog> list(@RequestBody DataTable dt, ServletRequest request) {
        return slifeLogService.pageSearch(dt);
    }

    @ApiOperation(value = "批量删除日志记录", notes = "批量删除日志记录传入日志ids")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = slifeLogService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();

    }


}
