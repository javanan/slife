package com.slife.java8.aspect;

import org.springframework.stereotype.Service;

/**
 * Created by chen on 2017/10/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
public class JoinpointTest {

    public void JoinpointTest(){
        System.out.println("**********JoinpointTest*****************");
    }

    public void test(){
        System.out.println("JoinpointTest++++执行我正常流水线的业务逻辑");
    }
}
