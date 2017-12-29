package com.example.administrator.lifecycleframework.ui.home;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.binding.FragmentDataBindingComponent;
import com.example.administrator.lifecycleframework.databinding.FragmentTechBinding;
import com.example.administrator.lifecycleframework.di.Injectable;
import com.example.administrator.lifecycleframework.util.AutoClearedValue;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/12/29.
 */

public class TechFragment extends SupportFragment implements Injectable {


    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<FragmentTechBinding> binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTechBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_tech, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);




    }
}
