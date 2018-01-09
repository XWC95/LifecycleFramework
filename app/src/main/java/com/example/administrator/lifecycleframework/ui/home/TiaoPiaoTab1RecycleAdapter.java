package com.example.administrator.lifecycleframework.ui.home;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.databinding.ItemTiaopiaoAdvertisingBinding;
import com.example.administrator.lifecycleframework.databinding.ItemTiaopiaoBannerBinding;
import com.example.administrator.lifecycleframework.databinding.ItemTiaopiaoHotpointsBinding;
import com.example.administrator.lifecycleframework.databinding.ItemTiaopiaoTab12Binding;
import com.example.administrator.lifecycleframework.databinding.ItemTiaopiaoTab1Binding;
import com.example.administrator.lifecycleframework.ui.common.DataBoundViewHolder;

/**
 * Created by Administrator on 2018/1/4.
 */

public class TiaoPiaoTab1RecycleAdapter extends RecyclerView.Adapter<DataBoundViewHolder> {


    private int BANNER_ITEM = 0;
    private int DEFAULT_ITEM = 1;
    private int ADVERITISING_ITEM = 2;
    private int HOT_POINT_ITEM = 3;
    private int DEFAULT_ITEM2 = 4;

    private Context context;
    private final DataBindingComponent dataBindingComponent;

    public TiaoPiaoTab1RecycleAdapter(Context context, android.databinding.DataBindingComponent dataBindingComponent) {
        this.context = context;
        this.dataBindingComponent = dataBindingComponent;
    }

    @Override
    public DataBoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER_ITEM) {
            ItemTiaopiaoBannerBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_tiaopiao_banner, parent, false,
                            dataBindingComponent);


            return new BannerViewHolder<>(binding);

        } else if (viewType == ADVERITISING_ITEM) {
            ItemTiaopiaoAdvertisingBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_tiaopiao_advertising, parent, false,
                            dataBindingComponent);
            return new AdveritisingViewHolder<>(binding);

        } else if (viewType == HOT_POINT_ITEM) {
            ItemTiaopiaoHotpointsBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_tiaopiao_hotpoints, parent, false,
                            dataBindingComponent);
            return new HotPointViewHolder<>(binding);
        }else if(viewType == DEFAULT_ITEM2){
            ItemTiaopiaoTab12Binding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_tiaopiao_tab1_2, parent, false,
                            dataBindingComponent);
            return new ViewHolder2<>(binding);
        } else {
            ItemTiaopiaoTab1Binding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_tiaopiao_tab1, parent, false,
                            dataBindingComponent);
            return new ViewHolder<>(binding);
        }
    }

    @Override
    public void onBindViewHolder(DataBoundViewHolder holder, int position) {
        if (getItemViewType(position) == HOT_POINT_ITEM) {
            HotPointViewHolder hotPointViewHolder = (HotPointViewHolder) holder;
            ItemTiaopiaoHotpointsBinding binding = (ItemTiaopiaoHotpointsBinding) hotPointViewHolder.binding;
            binding.hotPointRecycle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            binding.hotPointRecycle.setAdapter(new HotPointAdapter(context));


        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_ITEM;
        } else if (position == 1) {
            return ADVERITISING_ITEM;
        } else if (position == 7) {
            return HOT_POINT_ITEM;
        }else if(position >7){
            return DEFAULT_ITEM2;
        } else {
            return 100;
        }
    }


    @Override
    public int getItemCount() {
        return 50;
    }

    static class ViewHolder<T extends ViewDataBinding> extends DataBoundViewHolder<T> {
        public ViewHolder(T binding) {
            super(binding);
        }
    }

    static class ViewHolder2<T extends ViewDataBinding> extends DataBoundViewHolder<T> {
        public ViewHolder2(T binding) {
            super(binding);
        }
    }

    static class BannerViewHolder<T extends ViewDataBinding> extends DataBoundViewHolder<T> {
        public BannerViewHolder(T binding) {
            super(binding);
        }
    }

    static class AdveritisingViewHolder<T extends ViewDataBinding> extends DataBoundViewHolder<T> {
        public AdveritisingViewHolder(T binding) {
            super(binding);
        }
    }

    class HotPointViewHolder<T extends ViewDataBinding> extends DataBoundViewHolder<T> {
        HotPointViewHolder(T binding) {
            super(binding);
        }
    }
}
