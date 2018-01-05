package com.example.administrator.lifecycleframework.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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
import com.example.administrator.lifecycleframework.vo.Status;
import com.example.administrator.lifecycleframework.widget.RecyclerRefreshLayout;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_LOADING;
import static com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter.STATE_NO_MORE;

/**
 * Created by Administrator on 2017/12/29.
 */

public class TechFragment extends SupportFragment implements Injectable, RecyclerRefreshLayout.SuperRefreshLayoutListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    AutoClearedValue<FragmentTechBinding> binding;

    private TechViewModel viewModel;
    private TechAdapter adapter;

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

        init();
        initData();
    }

    private void initData() {
        viewModel.setTechId("Android", 0);
        viewModel.getTech().observe(this, listResource -> {
            if (listResource.status == Status.SUCCESS) {
                if (listResource.data != null && listResource.data.size() > 0) {

                    if (listResource.data.get(0).page == 1) {  //下拉刷新
                        binding.get().refresh.setRefreshing(false);
                        adapter.replace(listResource.data);
                    } else {  //加载更多

                        if (listResource.data.size() < 10) {
                            adapter.setState(STATE_NO_MORE, true);
                            binding.get().refresh.setOnLoading(true);  //设置不可加更多
                        } else {
                            binding.get().refresh.setOnLoading(false);  //设置可加加载
                        }

                        listResource.data.addAll(0, adapter.getItems());
                        adapter.replace(listResource.data);
                    }


                }
            }
        });
    }

    private void init() {
        adapter = new TechAdapter();
        binding.get().courseList.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TechViewModel.class);
        binding.get().refresh.setSuperRefreshLayoutListener(this);
    }

    @Override
    public void onRefreshing() {
        viewModel.refresh();
    }

    @Override
    public void onLoadMore() {
        adapter.setState(STATE_LOADING, true);
        viewModel.loadMore();
    }
}
