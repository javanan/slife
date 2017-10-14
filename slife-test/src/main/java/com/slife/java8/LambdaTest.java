package com.slife.java8;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chen on 2017/10/13.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class LambdaTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("3", "1", "2");
        //循环输出

        list.forEach(e -> System.out.println(e));


        //排序
        list.sort((e1, e2) -> {
            int r = e1.compareTo(e2);
            return r;
        });
        list.forEach(e -> System.out.println(e));


    }
}
