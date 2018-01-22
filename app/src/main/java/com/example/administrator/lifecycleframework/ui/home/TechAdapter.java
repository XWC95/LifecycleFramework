package com.example.administrator.lifecycleframework.ui.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.databinding.ItemTechBinding;
import com.example.administrator.lifecycleframework.databinding.LayoutRecyclerFooterViewBinding;
import com.example.administrator.lifecycleframework.ui.common.DataBoundListAdapter;
import com.example.administrator.lifecycleframework.util.Objects;
import com.example.administrator.lifecycleframework.vo.TechBean;

/**
 * Created by Administrator on 2018/1/2.
 */

public class TechAdapter extends DataBoundListAdapter<TechBean, ItemTechBinding> {

    public TechAdapter() {
        super(NEITHER);
    }

    @Override
    protected ItemTechBinding createBinding(ViewGroup parent) {
        ItemTechBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_tech, parent, false);
        return binding;
    }

    @Override
    protected void bindFooter(LayoutRecyclerFooterViewBinding binding, int mState) {
        binding.setState(mState);
    }

    @Override
    protected void bind(ItemTechBinding binding, TechBean item) {
        binding.setTech(item);
    }

    //判断是否是同一个Item。
    @Override
    protected boolean areItemsTheSame(TechBean oldItem, TechBean newItem) {
        return Objects.equals(oldItem._id, newItem._id);
    }

    //如果是同一个Item，此方法用于判断是否同一个 Item 的内容也相同。
    @Override
    protected boolean areContentsTheSame(TechBean oldItem, TechBean newItem) {
        return Objects.equals(oldItem.publishedAt, newItem.publishedAt)
                && oldItem.desc == newItem.desc && oldItem.who == newItem.who;
    }
}
