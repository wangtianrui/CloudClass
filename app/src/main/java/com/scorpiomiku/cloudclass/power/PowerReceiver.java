package com.scorpiomiku.cloudclass.power;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/7/4.
 */

public class PowerReceiver extends BroadcastReceiver {
    private String courseId;
    private String userId;

    public PowerReceiver(String courseId, String userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int rawlevel = intent.getIntExtra("level", -1);
        int scale = intent.getIntExtra("scale", -1);
        int level = -1;
        int status = intent.getIntExtra("status", -1);
        if (rawlevel >= 0 && scale > 0) {
            level = (rawlevel * 100) / scale;
        }
        String batteryStatus = "";
        StringBuilder sb = new StringBuilder();
        switch (status) {
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                batteryStatus = "[没有安装电池]";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                batteryStatus = "[正在充电]";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                batteryStatus = "[已经充满]";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                batteryStatus = "[放电中]";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                batteryStatus = "[未充电]";
                break;
            default:
                if (level <= 10)
                    sb.append("[电量过低，请充电]");
                else if (level <= 100) {
                    sb.append("[未连接充电器]");
                }
                break;
        }
        sb.append(batteryStatus);
        MessageUtils.logd(courseId + "-" + userId + ":power:" + level + "\t" + sb);
        HashMap<String, String> data = new HashMap<>();
        data.put("studentId", userId);
        data.put("courseId", courseId);
        data.put("status", sb.toString());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        data.put("time", hour + "");
        data.put("level", String.valueOf(level));
        WebUtils.addPower(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
