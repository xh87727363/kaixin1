package com.kaixin.testfor.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class MyApplication extends Application {
    private static MyApplication myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        Fresco.initialize(this);//初始化
        initOkHttp();
        ZXingLibrary.initDisplayOpinion(this);

    }

    public void initOkHttp() {

        //cookie 的知道花管理
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        //https 的支持
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,null,null);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(client);

    }

    public static Context getMyApplictionContext(){
        return myApp.getApplicationContext();
    }
}
