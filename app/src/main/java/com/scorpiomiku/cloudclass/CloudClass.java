package com.scorpiomiku.cloudclass;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.scorpiomiku.cloudclass.bean.Course;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClass extends Application {
    public static CloudClass mInstance;
    public static User user;
    public static Course course;
    public static String signCode;
    public static int position;


    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }




}
