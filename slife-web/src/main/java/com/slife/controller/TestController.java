package com.slife.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.util.PinyinUtils;
import com.slife.util.ReturnDTOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chen on 2017/8/16.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Controller
public class TestController {

    @GetMapping(value = "/test")
    @ResponseBody
    public ReturnDTO index(Model model, HttpServletRequest request, @RequestParam("chinese")String chinene) {

        return ReturnDTOUtil.success(PinyinUtils.ToFirstStringChar(chinene));

    }
}
