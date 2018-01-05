package com.example.administrator.lifecycleframework.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.databinding.FragmentTaoPiaoBinding;
import com.example.administrator.lifecycleframework.di.Injectable;
import com.example.administrator.lifecycleframework.util.AutoClearedValue;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/1/4.
 */

public class TaoPiaoFragment extends SupportFragment implements Injectable {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    AutoClearedValue<FragmentTaoPiaoBinding> binding;

    private TaoPiaoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTaoPiaoBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_tao_piao, container, false);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        init();
        initData();
    }

    private void initData() {

    }

    private void init() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaoPiaoViewModel.class);

        viewModel.initMagicIndicator(_mActivity, binding);
        viewModel.initViewPager(getChildFragmentManager());

    }
}
