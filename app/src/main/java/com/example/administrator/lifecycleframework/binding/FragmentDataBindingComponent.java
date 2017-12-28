package com.example.administrator.lifecycleframework.binding;


import android.databinding.DataBindingComponent;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/12/27.
 */

public class FragmentDataBindingComponent implements DataBindingComponent {
    private final FragmentBindingAdapters adapter;

    public FragmentDataBindingComponent(Fragment fragment) {
        this.adapter = new FragmentBindingAdapters(fragment);
    }

    @Override
    public FragmentBindingAdapters getFragmentBindingAdapters() {
        return adapter;
    }
}
