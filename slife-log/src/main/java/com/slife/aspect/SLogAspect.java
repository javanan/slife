package com.slife.aspect;


import com.slife.util.IPUtils;
import com.slife.util.ServletUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Aspect
@Component
public class SLogAspect {

    /**
     * 保存日志到数据库的线程池
     */
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    @Pointcut("@annotation(com.slife.annotation.SLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;


        // 获取request
        HttpServletRequest request = ServletUtils.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request); //获取请求的ip
        SaveLogTask saveLogTask = new SaveLogTask(point, time, ip);
        executor.execute(saveLogTask);//保存日志到数据库

        return result;
    }


}
