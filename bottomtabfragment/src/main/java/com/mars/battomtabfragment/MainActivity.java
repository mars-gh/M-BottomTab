package com.mars.battomtabfragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mars.battomtabfragment.adapter.TabAdapter;
import com.mars.battomtabfragment.fragment.Home1TabFragment;
import com.mars.battomtabfragment.fragment.Home2TabFragment;
import com.mars.battomtabfragment.fragment.Home3TabFragment;
import com.mars.battomtabfragment.fragment.Home4TabFragment;
import com.mars.battomtabfragment.fragment.Home5TabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.rb_home1)
    RadioButton rbHome1;
    @InjectView(R.id.rb_home2)
    RadioButton rbHome2;
    @InjectView(R.id.rb_home3)
    RadioButton rbHome3;
    @InjectView(R.id.rb_home4)
    RadioButton rbHome4;
    @InjectView(R.id.rb_home5)
    RadioButton rbHome5;
    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.rg_tab)
    RadioGroup rgTab;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //初始化底部tab按钮
        initTab();

        //初始化ViewPager
        initVP();
    }

    //初始化底部tab按钮
    private void initTab() {
        rgTab.setOnCheckedChangeListener(this);
    }

    //初始化ViewPager
    private void initVP() {
        fragments = new ArrayList<>();
        fragments.add(new Home1TabFragment());
        fragments.add(new Home2TabFragment());
        fragments.add(new Home3TabFragment());
        fragments.add(new Home4TabFragment());
        fragments.add(new Home5TabFragment());
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);

    }


    //RadioGroup监听
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        int item = 0;
        switch (checkedId) {
            case R.id.rb_home1:
                item = 0;
                break;
            case R.id.rb_home2:
                item = 1;
                break;
            case R.id.rb_home3:
                item = 2;
                break;
            case R.id.rb_home4:
                item = 3;
                break;
            case R.id.rb_home5:
                item = 4;
                break;
        }
        //ViewPager切换到对应的页面
        vp.setCurrentItem(item, false);//false 不需要Viewpager页面切换的时候有滑动的动画

    }
}
