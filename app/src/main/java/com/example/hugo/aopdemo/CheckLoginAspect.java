package com.example.hugo.aopdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 切面实现类
 * */
@Aspect
public class CheckLoginAspect {
    private static final String TAG = "checkLogin";

    /**
     * 找到处理的切点
     * * *(..)  可以处理CheckLogin这个类的所有的方法
     */
    @Pointcut("execution(@com.example.hugo.aopdemo.CheckLogin * *(..))")
    public void executionCheckLogin(){}

    /**
     * 处理切面
     * 类型	描述
     * Before	前置通知, 在目标执行之前执行通知
     * After	后置通知, 目标执行后执行通知
     * Around	环绕通知, 在目标执行中执行通知, 控制目标执行时机
     * AfterReturning	后置返回通知, 目标返回时执行通知
     * AfterThrowing	异常通知, 目标抛出异常时执行通知
     * */
    @Around("executionCheckLogin()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG,"checkLogin:");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckLogin checkLogin = signature.getMethod().getAnnotation(CheckLogin.class);
        if(checkLogin != null) {
            Context context = (Context) joinPoint.getThis();
            if (MyApp.isLogin()) {
                Log.i(TAG,"checkLogin:已登陆 ");
                //登陆成功继续运行方法里的程序
                return joinPoint.proceed();
            } else {
                Log.i(TAG,"checkLogin:未登录 ");
                Toast.makeText(context,"请登陆",Toast.LENGTH_LONG).show();
                return null;
            }
        }
        return joinPoint.proceed();
    }
}
