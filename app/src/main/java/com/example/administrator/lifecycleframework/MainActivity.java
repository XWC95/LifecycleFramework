package com.example.administrator.lifecycleframework;

import android.app.Activity;
import android.arch.lifecycle.LifecycleActivity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.administrator.lifecycleframework.databinding.ActivityMainBinding;
import com.example.administrator.lifecycleframework.ui.home.TechFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends SupportActivity implements HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private TechFragment techFragment;
    private TechFragment techFragment2;
    private TechFragment techFragment3;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        techFragment = new TechFragment();
        techFragment2 = new TechFragment();
        techFragment3 = new TechFragment();
        loadMultipleRootFragment(R.id.fl_main_content, 0, techFragment, techFragment2, techFragment3);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                showHideFragment(techFragment);
                break;
            case R.id.tow:
                showHideFragment(techFragment2);
                break;
            case R.id.thr:
                showHideFragment(techFragment3);
                break;
        }
        return true;
    }
}
