package com.example.administrator.lifecycleframework.ui.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.lifecycleframework.repository.TechRepository;

/**
 * Created by Administrator on 2017/12/29.
 */

public class TechViewModel extends ViewModel{

    final MutableLiveData<TechId> repoId;
//    private final LiveData<Resource<Repo>> repo;

    public TechViewModel(TechRepository repository) {
        repoId = new MutableLiveData<>();

    }


    static class TechId{

    }
}
