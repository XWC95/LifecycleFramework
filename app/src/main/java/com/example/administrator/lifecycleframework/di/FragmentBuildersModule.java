
package com.example.administrator.lifecycleframework.di;


import com.example.administrator.lifecycleframework.ui.home.TechFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TechFragment contributeTechFragment();
}
