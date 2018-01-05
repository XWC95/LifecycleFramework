package com.example.administrator.lifecycleframework;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.administrator.lifecycleframework.ui.home.TaoPiaoFragment;
import com.example.administrator.lifecycleframework.ui.home.TechFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity implements HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private TaoPiaoFragment taoPiaoFragment;
    private TechFragment techFragment2;
    private TechFragment techFragment3;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taoPiaoFragment = new TaoPiaoFragment();
        techFragment2 = new TechFragment();
        techFragment3 = new TechFragment();
        loadMultipleRootFragment(R.id.fl_main_content, 0, taoPiaoFragment, techFragment2, techFragment3);

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
                showHideFragment(taoPiaoFragment);
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
