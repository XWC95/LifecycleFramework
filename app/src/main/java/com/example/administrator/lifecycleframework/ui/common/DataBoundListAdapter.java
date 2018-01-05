package com.example.administrator.lifecycleframework.ui.common;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.databinding.LayoutRecyclerFooterViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public abstract class DataBoundListAdapter<T, V extends ViewDataBinding>
        extends RecyclerView.Adapter<DataBoundViewHolder<V>> {


    @Nullable
    private List<T> items;
    // each time data is set, we update this variable so that if DiffUtil calculation returns
    // after repetitive updates, we can ignore the old calculation
    private int dataVersion = 0;

    public static final int STATE_NO_MORE = 1;
    public static final int STATE_LOAD_MORE = 2;
    public static final int STATE_INVALID_NETWORK = 3;
    public static final int STATE_HIDE = 5;
    public static final int STATE_REFRESHING = 6;
    public static final int STATE_LOAD_ERROR = 7;
    public static final int STATE_LOADING = 8;

    public final int BEHAVIOR_MODE;
    protected int mState;

    public static final int NEITHER = 0;
    public static final int ONLY_HEADER = 1;
    public static final int ONLY_FOOTER = 2;
    public static final int BOTH_HEADER_FOOTER = 3;

    public static final int VIEW_TYPE_NORMAL = 0;
    public static final int VIEW_TYPE_HEADER = -1;
    public static final int VIEW_TYPE_FOOTER = -2;


    public DataBoundListAdapter(int mode) {
        items = new ArrayList<>();
        BEHAVIOR_MODE = mode;
        mState = STATE_HIDE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && (BEHAVIOR_MODE == ONLY_HEADER || BEHAVIOR_MODE == BOTH_HEADER_FOOTER))
            return VIEW_TYPE_HEADER;
        if (position + 1 == getItemCount() && (BEHAVIOR_MODE == ONLY_FOOTER || BEHAVIOR_MODE == BOTH_HEADER_FOOTER))
            return VIEW_TYPE_FOOTER;
        else return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (BEHAVIOR_MODE == ONLY_FOOTER || BEHAVIOR_MODE == ONLY_HEADER) {
            return items.size() + 1;
        } else if (BEHAVIOR_MODE == BOTH_HEADER_FOOTER) {
            return items.size() + 2;
        } else return items.size();
    }

    public int getCount() {
        return items.size();
    }

    @Nullable
    public List<T> getItems() {
        return items;
    }

    public void setState(int mState, boolean isUpdate) {
        this.mState = mState;
        if (isUpdate)
            updateItem(getItemCount() - 1);
    }

    public void updateItem(int position) {
        if (getItemCount() > position) {
            notifyItemChanged(position);
        }
    }

    @Override
    public final DataBoundViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                //TODO
            case VIEW_TYPE_FOOTER:
                return new DataBoundViewHolder<>(footBinding(parent));
            default:
                V binding = createBinding(parent);
                return new DataBoundViewHolder<>(binding);
        }

    }

    protected abstract V createBinding(ViewGroup parent);

    protected V footBinding(ViewGroup parent) {
        V binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.layout_recycler_footer_view, parent, false);
        return binding;
    }


    @Override
    public final void onBindViewHolder(DataBoundViewHolder<V> holder, int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                break;
            case VIEW_TYPE_FOOTER:
                LayoutRecyclerFooterViewBinding binding = (LayoutRecyclerFooterViewBinding) holder.binding;
                switch (mState) {
                    case STATE_INVALID_NETWORK:
                        binding.tvFooter.setText("网络错误");
                        binding.pbFooter.setVisibility(View.GONE);
                        break;
                    case STATE_LOAD_MORE:
                    case STATE_LOADING:
                        binding.tvFooter.setText("正在加载…");
                        binding.pbFooter.setVisibility(View.VISIBLE);
                        break;
                    case STATE_NO_MORE:
                        binding.tvFooter.setText("没有更多数据");
                        binding.pbFooter.setVisibility(View.GONE);
                        break;
                    case STATE_REFRESHING:
                        binding.tvFooter.setText("正在刷新");
                        binding.pbFooter.setVisibility(View.GONE);
                        break;
                    case STATE_LOAD_ERROR:
                        binding.tvFooter.setText("加载失败");
                        binding.pbFooter.setVisibility(View.GONE);
                        break;
                    case STATE_HIDE:
                        binding.getRoot().setVisibility(View.GONE);
                        break;
                }
                break;
            default:
                //noinspection ConstantConditions
                bind(holder.binding, items.get(position));
                holder.binding.executePendingBindings();
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(List<T> update) {
        dataVersion++;
        if (items == null) {
            if (update == null) {
                return;
            }
            items = update;
            notifyDataSetChanged();
        } else if (update == null) {
            int oldSize = items.size();
            items = null;
            notifyItemRangeRemoved(0, oldSize);
        } else {
            final int startVersion = dataVersion;
            final List<T> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            T oldItem = oldItems.get(oldItemPosition);
                            T newItem = update.get(newItemPosition);
                            return DataBoundListAdapter.this.areItemsTheSame(oldItem, newItem);
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            T oldItem = oldItems.get(oldItemPosition);
                            T newItem = update.get(newItemPosition);
                            return DataBoundListAdapter.this.areContentsTheSame(oldItem, newItem);
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if (startVersion != dataVersion) {
                        // ignore update
                        return;
                    }
                    items = update;
                    diffResult.dispatchUpdatesTo(DataBoundListAdapter.this);

                }
            }.execute();
        }
    }


    protected abstract void bind(V binding, T item);

    protected abstract boolean areItemsTheSame(T oldItem, T newItem);

    protected abstract boolean areContentsTheSame(T oldItem, T newItem);

}
