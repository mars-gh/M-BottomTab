package com.mars.bottomtab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //底部tab按钮监听
        setBtListener();

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

    private View.OnClickListener ChildAtClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //子类角标
            int indexOfChild = mainLlContainer.indexOfChild(v);

            //改变底部tab按钮显示状态
            switchTabShow(indexOfChild);
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

}
