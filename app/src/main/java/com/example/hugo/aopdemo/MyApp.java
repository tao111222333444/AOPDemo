package com.example.hugo.aopdemo;

import android.app.Application;

public class MyApp extends Application {
    private static boolean isLogin = true;

    public static boolean isLogin(){
        return isLogin;
    }


    public static void setIsLogin(boolean isLogin) {
        MyApp.isLogin = isLogin;
    }
}
