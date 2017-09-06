package com.mars.bottomtabvpfragment.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mars.battomtabfragment.adapter.TabAdapter;
import com.mars.battomtabfragment.fragment.Home1TabFragment;
import com.mars.battomtabfragment.fragment.Home2TabFragment;
import com.mars.battomtabfragment.fragment.Home3TabFragment;
import com.mars.battomtabfragment.fragment.Home4TabFragment;
import com.mars.battomtabfragment.fragment.Home5TabFragment;
import com.mars.bottomtabvpfragment.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.mars.bottomtabvpfragment.R.id.rb_home1;
import static com.mars.bottomtabvpfragment.R.id.rb_home2;
import static com.mars.bottomtabvpfragment.R.id.rb_home3;
import static com.mars.bottomtabvpfragment.R.id.rb_home4;
import static com.mars.bottomtabvpfragment.R.id.rb_home5;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(rb_home1)
    RadioButton rbHome1;
    @InjectView(rb_home2)
    RadioButton rbHome2;
    @InjectView(rb_home3)
    RadioButton rbHome3;
    @InjectView(rb_home4)
    RadioButton rbHome4;
    @InjectView(rb_home5)
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
        initSlidingMenu();

    }


    private int getDefaultScreenWidth(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;
    }
    //初始化侧滑菜单
    private void initSlidingMenu() {
        //创建侧滑菜单
        SlidingMenu slidingmenu = new SlidingMenu(this);
        //设置菜单从左边滑出
        slidingmenu.setMode(SlidingMenu.LEFT);
        //设置全屏可以滑出菜单
        slidingmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置侧滑菜单的宽度
        slidingmenu.setBehindWidth(getDefaultScreenWidth()*2/3);
        //把侧滑菜单附加在Activity里面
        slidingmenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置侧滑菜单的布局
        slidingmenu.setMenu(R.layout.left_menu);


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
            case rb_home1:
                item = 0;
                break;
            case rb_home2:
                item = 1;
                break;
            case rb_home3:
                item = 2;
                break;
            case rb_home4:
                item = 3;
                break;
            case rb_home5:
                item = 4;
                break;
        }
        //ViewPager切换到对应的页面
        vp.setCurrentItem(item, false);//false 不需要Viewpager页面切换的时候有滑动的动画

    }

}
