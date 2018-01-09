package com.example.administrator.lifecycleframework.ui.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.util.ScreenUtil;

/**
 * Created by Administrator on 2018/1/9.
 */

public class HotPointAdapter extends RecyclerView.Adapter<HotPointAdapter.ViewHolder> {

    private Context ctx;

    public HotPointAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_tiaopiao_hotpoint_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ScreenUtil.dip2px(155), ScreenUtil.dip2px(100));
            params.leftMargin = ScreenUtil.dip2px(16);
            params.rightMargin = ScreenUtil.dip2px(5);
            holder.imageView.setLayoutParams(params);
        } else if (position == getItemCount() - 1) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ScreenUtil.dip2px(155), ScreenUtil.dip2px(100));
            params.rightMargin = ScreenUtil.dip2px(16);
            holder.imageView.setLayoutParams(params);
        } else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ScreenUtil.dip2px(155), ScreenUtil.dip2px(100));
            params.rightMargin = ScreenUtil.dip2px(5);
            holder.imageView.setLayoutParams(params);
        }


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
