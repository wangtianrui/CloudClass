package com.scorpiomiku.cloudclass.utils;

import okhttp3.OkHttpClient;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class WebUtils {
    private static WebUtils instance = new WebUtils();
    private OkHttpClient mClient = new OkHttpClient();

    private WebUtils() {
    }

    public static WebUtils getInstance() {
        return instance;
    }

}
