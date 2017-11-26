package com.slife.controller;

import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: felixu.
 * @createTime: 2017/11/26.
 */
@RestController
@RequestMapping("/model")
@Api(description = "流程模型前端控制器")
public class ModelController {

    @Autowired
    ModelService modelService;

    @ApiOperation(value = "添加流程模型", notes = "添加流程模型")
    @PostMapping("")
    public String createModel(@RequestParam String name, @RequestParam String key,
                               @RequestParam String desc, @RequestParam String category) {
        if (null == name || null == key || null == category) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        Model model = modelService.create(name, key, desc, category);
        return model.getId();
    }
}