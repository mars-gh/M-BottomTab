package com.mars.bottomtab2fragment.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mars.bottomtab2fragment.R;

/**
 * Created by mars on 2017/9/5.
 */

public class Home1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView v = (TextView)view.findViewById(R.id.tv);
        v.setText("首页1");
        v.setGravity(Gravity.CENTER);
        v.setTextColor(Color.RED);
        v.setTextSize(30);
    }
}
