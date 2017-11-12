package com.slife.java8.aspect;


import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author chen
 * @date 2017/10/27
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Aspect // FOR AOP
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class AdviceTest {

@Pointcut(value="execution(* com.slife.java8.aspect.AspectTest.test())")
    public void poincut(){

    }

    @Before("poincut()")
    public void beforeTest() {
        System.out.println("执行 方法 之前 调用----");
    }

    @After("execution(* com.slife.java8.aspect.AspectTest.test())")
    public void afterTest() {
        System.out.println();
        System.out.println("执行 方法 之后 调用----");
    }

    @Around("execution(* com.slife.java8.aspect.AspectTest.test())")
    public void aroundTest() {
        System.out.println();
        System.out.println("执行 方法 前后 调用----");
    }



    @AfterReturning("execution(* com.slife.java8.aspect.AspectTest.test())")
    public void afterReturningTest() {
        System.out.println();
        System.out.println("执行 方法 AfterReturning 调用----");
    }



    @AfterThrowing("execution(* com.slife.java8.aspect.AspectTest.test())")
    public void afterThrowingTest() {
        System.out.println();
        System.out.println("执行 方法 AfterThrowing 调用----");
    }


    @Before("execution(* com.slife.java8..*test*(..))")
    public void aspecttest1() {
        System.out.println();
        System.out.println("执行 方法aspecttest1  Before 调用----");
    }


}
