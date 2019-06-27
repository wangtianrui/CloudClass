package com.scorpiomiku.cloudclass.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class CommonUtils {
    /**
     * 打电话
     *
     * @param context
     * @param phone
     */
    @SuppressLint("MissingPermission")
    public static void call(Activity context, String phone) {
        if (StringUtils.isNotEmpty(phone, true)) {
            Uri uri = Uri.parse("tel:" + phone.trim());
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            context.startActivity(intent);
            return;
        }
        MessageUtils.makeToast("请先选择号码哦~");
    }

    /**
     * 发送信息，单个号码
     *
     * @param context
     * @param phone
     */
    public static void toMessageChat(Activity context, String phone) {
        if (context == null || StringUtils.isNotEmpty(phone, true) == false) {
            MessageUtils.logd("sendMessage  context == null || StringUtils.isNotEmpty(phone, true) == false) >> return;");
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("address", phone);
        intent.setType("vnd.android-dir/mms-sms");
        context.startActivity(intent);
    }

}
