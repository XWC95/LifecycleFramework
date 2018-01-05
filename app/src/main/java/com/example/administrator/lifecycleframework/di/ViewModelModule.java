package com.example.administrator.lifecycleframework.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.example.administrator.lifecycleframework.ui.home.TaoPiaoTab1ViewModel;
import com.example.administrator.lifecycleframework.ui.home.TaoPiaoViewModel;
import com.example.administrator.lifecycleframework.ui.home.TechViewModel;
import com.example.administrator.lifecycleframework.viewmodel.ProjectViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ProjectViewModelFactory factory);


    @Binds
    @IntoMap
    @ViewModelKey(TechViewModel.class)
    abstract ViewModel bindTechViewModel(TechViewModel techViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TaoPiaoViewModel.class)
    abstract ViewModel bindTaoPiaoViewModel(TaoPiaoViewModel techViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TaoPiaoTab1ViewModel.class)
    abstract ViewModel bindTaoPiaoTab1ViewModel(TaoPiaoTab1ViewModel taoPiaoTab1ViewModel);

}
