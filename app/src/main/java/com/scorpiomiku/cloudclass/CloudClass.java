package com.scorpiomiku.cloudclass;

import android.app.Application;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClass extends Application {
    public static CloudClass mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


}
