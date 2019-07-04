package com.scorpiomiku.cloudclass;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;

import com.scorpiomiku.cloudclass.bean.Course;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.power.PowerService;


/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClass extends Application {
    public static CloudClass mInstance;
    public static User user = new User();
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
