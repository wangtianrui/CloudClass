package com.scorpiomiku.cloudclass.utils;

import com.scorpiomiku.cloudclass.bean.User;

import java.util.HashMap;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class WebUtils {
    private static WebUtils instance = new WebUtils();
    private static OkHttpClient mClient = new OkHttpClient();

    private WebUtils() {
    }

    public static WebUtils getInstance() {
        return instance;
    }

    /**
     * 注册
     *
     * @param data
     * @param callback
     */
    public static void rigester(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "register/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 登录
     *
     * @param data
     * @param callback
     * @return
     */
    public static void login(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "login/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 添加个人信息
     *
     * @param data
     * @param callback
     */
    public static void addInformation(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "complete_information/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    public static FormBody getBody(HashMap<String, String> data) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            bodyBuilder.add(key, data.get(key));
        }
        return bodyBuilder.build();
    }
}
