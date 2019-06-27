package com.scorpiomiku.cloudclass;

import android.app.Application;

import com.scorpiomiku.cloudclass.bean.Course;
import com.scorpiomiku.cloudclass.bean.User;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClass extends Application {
    public static CloudClass mInstance;
    public static User user;
    public static Course course;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


}
