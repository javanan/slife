package com.slife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chen on 2017/9/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Controller
@RequestMapping(value = "/test1")
public class SysTestController {
   // @Autowired
   // private StringRedisTemplate stringRedisTemplate;

/*    @ResponseBody
    @GetMapping(value = "/redisTest")
    public void redisTest(){
        stringRedisTemplate.opsForValue().set("xxx","dddd");
        stringRedisTemplate.opsForSet().add("2","x","c");
    }*/
}
