package com.example.administrator.lifecycleframework;

import android.app.Activity;
import android.app.Application;

import com.example.administrator.lifecycleframework.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/12/27.
 */

public class App extends Application implements HasActivityInjector {

    private static App instance;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;



    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        AppInjector.init(this);

    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
