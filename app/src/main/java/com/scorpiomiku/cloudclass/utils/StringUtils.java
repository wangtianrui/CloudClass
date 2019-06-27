package com.scorpiomiku.cloudclass.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class StringUtils {

    public static String timeFormat(String time) {
        return time.substring(0, 19).replace("T", " ");
    }

    /**
     * 判断字符是否非空
     *
     * @param s
     * @param trim
     * @return
     */
    public static boolean isNotEmpty(String s, boolean trim) {

        if (s == null) {
            return false;
        }
        if (trim) {
            s = s.trim();
        }
        if (s.length() <= 0) {
            return false;
        }

        return true;
    }



}
