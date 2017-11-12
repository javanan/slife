package com.slife.java8.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chen on 2017/10/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@RestController
public class AspectTest {

    @Autowired
    private JoinpointTest joinpointTest;

    @GetMapping(value = "/aspecttest")
    public void test(){
        joinpointTest.test();
    }


    @GetMapping(value = "/aspecttest1")
    public void test1(){
        joinpointTest.test();
    }

}
