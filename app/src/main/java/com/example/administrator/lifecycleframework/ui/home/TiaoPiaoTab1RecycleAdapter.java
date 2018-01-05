package com.example.administrator.lifecycleframework.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.lifecycleframework.R;

/**
 * Created by Administrator on 2018/1/4.
 */

public class TiaoPiaoTab1RecycleAdapter extends RecyclerView.Adapter<TiaoPiaoTab1RecycleAdapter.ViewHolder> {

    private Context context;

    public TiaoPiaoTab1RecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tiaopiao_tab1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 15;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
