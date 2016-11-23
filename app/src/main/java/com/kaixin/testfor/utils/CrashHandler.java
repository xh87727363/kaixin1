package com.kaixin.testfor.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private  static CrashHandler crashHandler;
    //系统默认的异常信息捕获
    private Thread.UncaughtExceptionHandler defaultHandler;
    private  CrashHandler (){
    }
    public static CrashHandler newInstance(){
        if (crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }
    public void init(){
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        if (!handException(e)){//交给系统处理异常
            defaultHandler.uncaughtException(thread,e);
        }else {//自己处理异常信息

        }

    }



    public boolean handException(Throwable throwable){
        if (throwable == null){
            return false;
        }
        String errorMessage = getStack(throwable);
        return  true;

    }

    private String getStack(Throwable throwable) {
        String errorMessage = "";
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(baos);
            throwable.printStackTrace(printStream);
            errorMessage = baos.toString();
        }catch (Exception e){
            errorMessage = e.toString();
        }
        return errorMessage;

    }
}
