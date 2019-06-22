package com.scorpiomiku.cloudclass.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public abstract class BaseFragment extends Fragment {
    private View myView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.myView = inflater.inflate(getLayoutId(), container, false);
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }



    protected abstract int getLayoutId();

    protected abstract void refreshData();

    protected abstract void initView();

    public View getMyView() {
        return myView;
    }
}
