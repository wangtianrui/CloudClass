package com.scorpiomiku.cloudclass.power;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.scorpiomiku.cloudclass.CloudClass;

/**
 * Created by ScorpioMiku on 2019/7/4.
 */

public class PowerService extends Service {
    private PowerReceiver powerReceiver;
    private Handler handler = new Handler();
    private Runnable runnable;
    private String courseId = CloudClass.course.getCourse_id();
    private String userId = CloudClass.user.getPhone();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        courseId = intent.getStringExtra("courseId");
        userId = intent.getStringExtra("userId");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        powerReceiver = new PowerReceiver(courseId, userId);
        runnable = new Runnable() {
            @Override
            public void run() {
                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                registerReceiver(powerReceiver, intentFilter);
                handler.postDelayed(this, 10000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}
