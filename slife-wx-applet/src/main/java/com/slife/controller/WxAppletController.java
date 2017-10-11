package com.slife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by chen on 2017/7/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Controller
public class WxAppletController {


    @GetMapping("/wxapplet")
    public String test() {

        return "test/wxapplet";
    }
}
