package com.mars.bottomtab2fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mars.bottomtab2fragment.fragment.Home1Fragment;
import com.mars.bottomtab2fragment.fragment.Home2Fragment;
import com.mars.bottomtab2fragment.fragment.Home3Fragment;
import com.mars.bottomtab2fragment.fragment.Home4Fragment;
import com.mars.bottomtab2fragment.fragment.Home5Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_fragment_container)
    //fragment
            FrameLayout mainFragmentContainer;
    @InjectView(R.id.main_ll_container)
    //底部tab
            LinearLayout mainLlContainer;

    //底部tab子類
    private int childCount;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //底部tab按钮监听
        setBtListener();

        //fragment初始化
        intFragment();

        //初始点击0，选中状态
        ChildAtClickListener.onClick(mainLlContainer.getChildAt(0));

    }


    //fragment初始化
    private void intFragment() {
        fragments = new ArrayList<>();
        fragments.add(new Home1Fragment());
        fragments.add(new Home2Fragment());
        fragments.add(new Home3Fragment());
        fragments.add(new Home4Fragment());
        fragments.add(new Home5Fragment());

    }

    //底部tab按钮监听
    private void setBtListener() {

        //各個tab
        childCount = mainLlContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = mainLlContainer.getChildAt(i);
            childAt.setOnClickListener(ChildAtClickListener);
        }
    }

    private OnClickListener ChildAtClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            //子类角标
            int indexOfChild = mainLlContainer.indexOfChild(v);

            //改变底部tab按钮显示状态
            switchTabShow(indexOfChild);

            //改变对应fragment
            switchFragment(indexOfChild);
        }
    };


    //改变底部tab按钮显示状态
    private void switchTabShow(int index) {
        for (int i = 0; i < childCount; i++) {
            // 判断i是否与index相同
            // 相同：不可用状态：enable=false
            if (i == index) {
                // 不可以再点击了
//                mainBottomeSwitcherContainer.getChildAt(i).setEnabled(false);
                // 每个Item中的控件都需要切换状态
                setEnable(mainLlContainer.getChildAt(i), false);

            } else {
                // 不可以再点击了
//                mainBottomeSwitcherContainer.getChildAt(i).setEnabled(true);
                // 每个Item中的控件都需要切换状态
                setEnable(mainLlContainer.getChildAt(i), true);
            }
        }
    }

    /**
     * 将每个Item中的所用控件状态一同改变
     * 由于我们处理一个通用的代码，那么Item可能会有很多层，所以我们需要使用递归
     *
     * @param item
     * @param b
     */
    private void setEnable(View item, boolean b) {
        item.setEnabled(b);
        if (item instanceof ViewGroup) {
            int childCount = ((ViewGroup) item).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i), b);
            }
        }
    }

    //改变对应fragment
    private void switchFragment(int index) {

        Fragment f = fragments.get(index);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, f).commit();
    }


}
