package com.example.administrator.lifecycleframework.di;

import android.arch.lifecycle.ViewModelProvider;


import com.example.administrator.lifecycleframework.viewmodel.ProjectViewModelFactory;

import dagger.Binds;
import dagger.Module;
@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ProjectViewModelFactory factory);


}
