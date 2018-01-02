package com.example.administrator.lifecycleframework.util;

import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/1/2.
 */

public class DateUtil {

    /**
     * 切割标准时间
     * @param time
     * @return
     */
    @Nullable
    public static String subStandardTime(String time) {
        int idx = time.indexOf(".");
        if (idx > 0) {
            return time.substring(0, idx).replace("T"," ");
        }
        return null;
    }
}
