package com.example.administrator.lifecycleframework.ui.home;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.administrator.lifecycleframework.R;
import com.example.administrator.lifecycleframework.databinding.FragmentTaoPiaoBinding;
import com.example.administrator.lifecycleframework.ui.common.ViewPagerAdapterFragment;
import com.example.administrator.lifecycleframework.util.AutoClearedValue;
import com.example.administrator.lifecycleframework.widget.magicindicator.MagicIndicator;
import com.example.administrator.lifecycleframework.widget.magicindicator.ViewPagerHelper;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.CommonNavigator;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.UIUtil;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.abs.CommonNavigatorAdapter;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.abs.IPagerIndicator;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.abs.IPagerTitleView;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.indicators.LinePagerIndicator;
import com.example.administrator.lifecycleframework.widget.magicindicator.commonnavigator.titles.SimplePagerTitleView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/4.
 */

public class TaoPiaoViewModel extends ViewModel {

    private List<String> mDataList = Arrays.asList(new String[]{"正在热映", "即将上映", "小视频", "排行榜"});

    private AutoClearedValue<FragmentTaoPiaoBinding> binding;
    private Context context;

    @Inject
    public TaoPiaoViewModel() {
    }


    public void initMagicIndicator(Context context, AutoClearedValue<FragmentTaoPiaoBinding> binding) {
        this.binding = binding;
        this.context = context;

        binding.get().magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                simplePagerTitleView.setNormalColor(Color.parseColor("#6A6B66"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#D35F61"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.get().viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
                indicator.setXOffset(UIUtil.dip2px(context, 33)); //设置偏移量（indicator宽）
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setColors(Color.parseColor("#D35F61"));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                if (index == 0) {
                    return 2.0f;
                } else if (index == 1) {
                    return 1.2f;
                } else {
                    return 1.0f;
                }
            }
        });
        binding.get().magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(binding.get().magicIndicator, binding.get().viewPager);
    }

    public void initViewPager(FragmentManager fm) {
        ViewPagerAdapterFragment adapter = new ViewPagerAdapterFragment(fm, context);
        adapter.addTab("正在热映", "1", TaoPiaoTab1Fragment.class, null);
        adapter.addTab("即将上映", "2", TaoPiaoTab1Fragment.class, null);
        adapter.addTab("小视频", "3", TaoPiaoTab1Fragment.class, null);
        adapter.addTab("排行榜", "4", TaoPiaoTab1Fragment.class, null);
        binding.get().viewPager.setOffscreenPageLimit(5);
        binding.get().viewPager.setAdapter(adapter);
    }
}
