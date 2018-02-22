package com.slife.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.cache.CacheUtils;
import com.slife.entity.SysMenu;
import com.slife.util.ReturnDTOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
/*    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


    @ResponseBody
    @GetMapping(value = "/redisTest")
    public void redisTest() {
        stringRedisTemplate.opsForValue().set("xxx", "dddd");
        stringRedisTemplate.opsForSet().add("2", "x", "c");

    }*/

    @ResponseBody
    @GetMapping(value = "/redisTest")
    public ReturnDTO redisTest() {
        CacheUtils.put("tn","k","dd");
        List<String> keys = CacheUtils.getKeys("books");
        List<String> tnkeys = CacheUtils.getKeys("tn");
        List<String> defaultCache = CacheUtils.getKeys("defaultCache");
        List<String> spring = CacheUtils.getKeys("spring");
        List<String> d = CacheUtils.getKeys("*");

        List<SysMenu> sysMenus = CacheUtils.get("books", 1);
        return ReturnDTOUtil.success(sysMenus);

    }
}
