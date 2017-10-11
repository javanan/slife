package com.slife.test;

import com.alibaba.fastjson.JSON;
import com.slife.util.page.PageUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by chen on 2017/9/11.
 * <p>
 * Describe:
 */
public class SubListTest {

    @Test
    public void subList() {
        List list = new ArrayList();
        for (int i = 1; i < 10; i++) {
            list.add("a" + i);
        }
        System.out.println(JSON.toJSON(list));
        List t = PageUtils.subList(1, 2, list);
        System.out.println(JSON.toJSON(t));

        String a="www";
        String b="www";

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }

    @Test
    public void option(){
        String  modelId=null;
        try {
            Optional.ofNullable(modelId).orElseThrow(()->new Exception("dddd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
