package com.example.administrator.lifecycleframework.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/27.
 */

public class FragmentBindingAdapters {

    final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }
    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String url) {
        Glide.with(fragment).load(url).into(imageView);
    }

    @BindingAdapter("localSrc")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }
}
