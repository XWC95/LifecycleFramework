package com.example.administrator.lifecycleframework.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.lifecycleframework.AppExecutors;
import com.example.administrator.lifecycleframework.api.ApiResponse;
import com.example.administrator.lifecycleframework.api.GankHttpResponse;
import com.example.administrator.lifecycleframework.api.OnlyService;
import com.example.administrator.lifecycleframework.db.TechDao;
import com.example.administrator.lifecycleframework.vo.Resource;
import com.example.administrator.lifecycleframework.vo.TechBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/12/29.
 */
@Singleton
public class TechRepository {

    private final TechDao techDao;
    private final OnlyService mService;
    private final AppExecutors mAppExecutors;

    @Inject
    public TechRepository(TechDao techDao, OnlyService mService, AppExecutors mAppExecutors) {
        this.techDao = techDao;
        this.mService = mService;
        this.mAppExecutors = mAppExecutors;
    }


    public LiveData<Resource<List<TechBean>>> getTechList(String tech, int page) {
        return new NetworkBoundResource<List<TechBean>, GankHttpResponse<List<TechBean>>>(mAppExecutors) {

            @Override
            protected void saveCallResult(@NonNull GankHttpResponse<List<TechBean>> item) {
                if (item.error) {
                    return;
                }
                techDao.insertTechs(item.results);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<TechBean> data) {
                return /*data == null || data.isEmpty()*/ true;
            }

            @NonNull
            @Override
            protected LiveData<List<TechBean>> loadFromDb() {
                return techDao.findTechList(page);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GankHttpResponse<List<TechBean>>>> createCall() {
                return mService.getTechList(tech, 10, page);
            }

            @Override
            protected GankHttpResponse<List<TechBean>> processResponse(ApiResponse<GankHttpResponse<List<TechBean>>> response) {
                if (!response.body.error) {
                    for (int i = 0; i < response.body.results.size() && response.body.results.size() > 0; i++) {
                        response.body.results.get(i).page = page;
                    }
                }
                return super.processResponse(response);
            }
        }.asLiveData();
    }
}
