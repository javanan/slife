package com.slife.java8;

import sun.awt.image.ImageWatched;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chen on 2017/10/23.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class StreamTest {

    public static void main(String[] args) {

        List<StreamEntity> list = Arrays.asList(new StreamEntity("张三", 19), new StreamEntity("李四", 20), new StreamEntity
                ("王五", 18), new StreamEntity("呵呵", 19));


/*
        int sum = list.stream().parallel().filter(u -> u.getAge() >= 19).mapToInt(u -> u.getAge()).reduce(0,(a,b)->a+b);
        int sum1 = list.stream().parallel().filter(u -> u.getAge() >= 19).mapToInt(u -> u.getAge()).reduce(0,
                Integer::sum);

        System.out.println(sum);
        System.out.println(sum1);
*/

  /*      Map<Integer, List< StreamEntity >> m = list.stream().parallel().collect(Collectors.groupingBy(streamEntity -> streamEntity.getAge()));

        m.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v)  );*/
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
    }
}
