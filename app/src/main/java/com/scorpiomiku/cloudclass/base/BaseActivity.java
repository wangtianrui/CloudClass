package com.scorpiomiku.cloudclass.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        iniview();
        initHandle();
    }

    protected abstract void initHandle();

    abstract public void iniview();

    abstract public int getLayoutId();

    abstract public void refreshData();

}
