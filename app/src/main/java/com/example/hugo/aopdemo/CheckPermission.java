package com.example.hugo.aopdemo;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面  检查权限的实现类
 *
 * Aspect的真正作用，它负责收集Jpoint，
 * 设置advice。一些简单的功能可在Aspect中来完成，
 * 而一些复杂的功能，则只是有Aspect来统一收集信息，并交给专业模块来处理。
 * */
@Aspect
public class CheckPermission {
    private static String TAG = "CheckPermission";

    /**
     * 来看这个Pointcut,
     * 首先，它在选择Joinpoint的时候，把@SecurityCheckAnnotation使用上了，
     * 这表面所有的  那些public 的，并且携带有这个注解的API都是目标JoinPoint，
     * 接着由于我们希望再函数中获取注解的信息，所有这里的pointcut函数有一个参数，
     * 参数类型是SecurityCheckAnnotation，参数名为ann
     * 这个参数我们需要再后面的advice里用上，所以pointcut还使用了@annotation(ann)这种方法来
     * 告诉AspectJ,这个ann是一个注解
     * */
    @Pointcut("execution(@SecurityCheckAnnotation  private * *..*.*(..)) && @annotation(ann)")
    public void checkPermssion(SecurityCheckAnnotation ann){};

    /**
     * 接下来是 advice，advice的真正功能是由check函数来实现的，这个check函数第二个参数就是我们想要的
     * 注解。再实际运行过程中，AspectJ会把这个信息从JoinPoint中提取出来并传递给check函数。
     */
    @Before("checkPermssion(securityCheckAnnotation)")
    public void check(JoinPoint joinPoint,SecurityCheckAnnotation securityCheckAnnotation){
        //从注解信息中获取声明的权限。
        String needsPermission = securityCheckAnnotation.declaredPermission();
        Log.e(TAG,joinPoint.toShortString());
        Log.e(TAG,"\tneeded permission is "+needsPermission);
        return;
    }
}
