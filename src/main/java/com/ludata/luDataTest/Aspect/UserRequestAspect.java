package com.ludata.luDataTest.Aspect;


import com.ludata.luDataTest.utils.NetworkUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 定义的是每个用户请求方法的日志信息面向切面类
 */
@Aspect
@Component
public class UserRequestAspect {

    @PostConstruct
    public void init() {
        System.out.println("已经对UserRequestAspect类进行了初始化");
    }


    /**
     * 定义那些方法需要被切面操作
     */
    @Pointcut("execution(public * com.ludata.luDataTest.controller.*.*Aspact*(..))")
    public void webLog(){

    }
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint point)throws  Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();     //获取当前request对象
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();  //获取当前response对象
        String targetObj = point.getTarget().getClass().getName();  //获取当前方法所属的类
        String methodName = point.getSignature().getName();         //获取方法名称
        Object[] objects = point.getArgs();                         //获取对应的请求参数
        Object methodResult=null;                                   //存储方法返回值内容
        Method method = ((MethodSignature) point.getSignature()).getMethod(); // 获得切入的方法
        try{
            System.out.println("执行当前方法之前");
            //获取当前请求的IP地址
            String ipAddress= NetworkUtil.getIpAddress(request);
            //方法执行前的毫秒数
            Long startTime=System.currentTimeMillis();

            //遍历出请求的参数信息
            for(Object obj:objects){
                System.out.println(obj.toString());
            }


            //执行当前请求的方法
            methodResult=point.proceed();

            //方法执行后的毫秒数
            Long endTime=System.currentTimeMillis();
            System.out.println("=============================");
            System.out.println("ip为："+ipAddress+"用户");
            System.out.println("请求了"+targetObj+"类中的"+methodName+"方法");
            System.out.println("整个方法执行了："+(endTime-startTime)+"毫秒");


            System.out.println("执行当前方法之后");
        }catch (Exception e){
            System.out.println("当前方法进入了异常");
            e.printStackTrace();
        }

        return methodResult;

    }



}
