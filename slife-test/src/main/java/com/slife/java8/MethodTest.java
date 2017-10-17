package com.slife.java8;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chen on 2017/10/16.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class MethodTest {

    public static void main(String[] args) {

        //构造器的引用
        User user = User.create(User::new);
        List<User> users = Arrays.asList(user);

        //静态方法引用
        users.forEach(User::name);

        //对象方法的引用
        users.forEach(user::age);

        String[] stringsArray = {"Hello", "World"};

        //使用lambda表达式和类型对象的实例方法
        Arrays.sort(stringsArray, (s1, s2) -> s1.compareToIgnoreCase(s2));

        //使用方法引用
        //引用的是类型对象的实例方法
        Arrays.sort(stringsArray, String::compareToIgnoreCase);

        Arrays.asList(stringsArray).stream().forEach(x -> System.out.println(x));
        //引用的是类型对象的实例方法
        Arrays.asList(stringsArray).stream().forEach(System.out::println);


    }


}
