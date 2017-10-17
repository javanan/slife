package com.slife.java8;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

/**
 * Created by chen on 2017/10/16.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class User {

    public static User create(Supplier<User> supplier) {
        System.out.println("create ");


        return supplier.get();
    }

    public static String name(User u) {
        System.out.println("name ");
        return "呵呵";
    }

    public int age(User u) {
        System.out.println("age ");
        return 18;
    }

}
