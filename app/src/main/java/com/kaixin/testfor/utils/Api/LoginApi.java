package com.kaixin.testfor.utils.Api;

import android.util.Log;

import com.kaixin.testfor.utils.APIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class LoginApi {
    public static void login(String mobile,String password){
        HashMap<String ,String > map = new HashMap<>();
        map.put("usrename",mobile);
        map.put("password",password);
        OkHttpUtils.get().url("https://github.com/hongyangAndroid/okhttp-utils")
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .params(map)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        Log.d("LoginApi", "结果：response:" + response);
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Object response, int id) {

                    }
                });
    }
}
