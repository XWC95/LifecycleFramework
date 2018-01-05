
package com.example.administrator.lifecycleframework.di;


import com.example.administrator.lifecycleframework.ui.home.TaoPiaoFragment;
import com.example.administrator.lifecycleframework.ui.home.TaoPiaoTab1Fragment;
import com.example.administrator.lifecycleframework.ui.home.TechFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TechFragment contributeTechFragment();

    @ContributesAndroidInjector
    abstract TaoPiaoFragment contributTaoPiaoFragment();

    @ContributesAndroidInjector
    abstract TaoPiaoTab1Fragment contributTaoPiaoTab1Fragment();

}
