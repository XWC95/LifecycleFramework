package com.example.administrator.lifecycleframework.api;


import android.arch.lifecycle.LiveData;

import com.example.administrator.lifecycleframework.vo.TechBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface OnlyService {


    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    LiveData<ApiResponse<GankHttpResponse<List<TechBean>>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

}
