package com.example.administrator.lifecycleframework.util;

import android.support.annotation.Nullable;
import android.view.View;

import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_INVALID_NETWORK;
import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_LOADING;
import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_LOAD_ERROR;
import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_NO_MORE;
import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_REFRESHING;

/**
 * Created by Administrator on 2018/1/2.
 */

public class DateUtil {

    /**
     * 切割标准时间
     *
     * @param time
     * @return
     */
    @Nullable
    public static String subStandardTime(String time) {
        int idx = time.indexOf(".");
        if (idx > 0) {
            return time.substring(0, idx).replace("T", " ");
        }
        return null;
    }

    public static String footerState(int state) {
        if (state == STATE_INVALID_NETWORK) {
            return "网络错误";
        } else if (state == STATE_LOADING) {
            return "正在加载…";
        } else if (state == STATE_NO_MORE) {
            return "没有更多数据";
        } else if (state == STATE_REFRESHING) {
            return "正在刷新";
        } else if (state == STATE_LOAD_ERROR) {
            return "加载失败";
        } else {
            return "";
        }

    }
}









