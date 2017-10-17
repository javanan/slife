package com.slife.java8;

import java.util.Optional;

/**
 * Created by chen on 2017/10/16.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class OptionalTest {


    public static void main(String[] args)  {
        Optional< String > fullName = Optional.ofNullable( null );
        //如果Optional实例持有一个非空值，则isPresent()方法返回true，否则返回false；
        System.out.println( "Full Name is set? " + fullName.isPresent() );

        //orElseGet()方法，Optional实例持有null，则可以接受一个lambda表达式生成的默认值
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );

        //map()方法可以将现有的Opetional实例的值转换成新的值；
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        //orElse()方法与orElseGet()方法类似，但是在持有null的时候返回传入的默认值
    }


}
