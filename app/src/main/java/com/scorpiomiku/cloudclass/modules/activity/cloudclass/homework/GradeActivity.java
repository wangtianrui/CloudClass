package com.scorpiomiku.cloudclass.modules.activity.cloudclass.homework;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.Answer;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.GradeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GradeActivity extends BaseActivity {


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<Answer> list;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        break;
                    case -1:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    public void iniview() {
        list = AnswerListActivity.list;
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return GradeFragment.newInstance(list.get(position), position, viewPager);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setCurrentItem(CloudClass.position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_grade;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
