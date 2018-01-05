package com.example.administrator.lifecycleframework.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.lifecycleframework.repository.TechRepository;
import com.example.administrator.lifecycleframework.util.AbsentLiveData;
import com.example.administrator.lifecycleframework.vo.Resource;
import com.example.administrator.lifecycleframework.vo.TechBean;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/29.
 */

public class TechViewModel extends ViewModel {

    final MutableLiveData<TechId> techId;
    private final LiveData<Resource<List<TechBean>>> tech;

    @Inject
    public TechViewModel(TechRepository repository) {
        techId = new MutableLiveData<>();

        tech = Transformations.switchMap(techId, input -> {
            if (input == null) {
                return AbsentLiveData.create();
            } else {
                return repository.getTechList(input.techType, input.page);
            }
        });
    }


    void refresh() {
        if (techId.getValue() != null) {
            techId.getValue().page = 1;
            techId.setValue(techId.getValue());
        }
    }

    void loadMore() {
        if (techId.getValue() != null) {
            techId.getValue().page++;
            TechId value = techId.getValue();
            techId.setValue(value);
        }

    }

    public LiveData<Resource<List<TechBean>>> getTech() {
        return tech;
    }

    public void setTechId(String techType, int page) {
        TechId techId = new TechId();
        techId.techType = techType;
        techId.page = page;
        this.techId.setValue(techId);
    }


    static class TechId {
        public String techType;
        public int page;
    }
}
