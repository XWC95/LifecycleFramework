
package com.example.administrator.lifecycleframework.di;


import com.example.administrator.lifecycleframework.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();

}
