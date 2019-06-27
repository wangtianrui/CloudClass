package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.UpFileActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.home_work_button)
    LinearLayout homeWorkButton;
    @BindView(R.id.up_button)
    LinearLayout upButton;
    @BindView(R.id.sign_button)
    LinearLayout signButton;
    @BindView(R.id.communication_button)
    LinearLayout communicationButton;
    @BindView(R.id.score_button)
    LinearLayout scoreButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ly_root_main)
    LinearLayout lyRootMain;
    Unbinder unbinder;

    @Override
    protected Handler initHandle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cloudclass_main_fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.home_work_button, R.id.up_button, R.id.sign_button, R.id.communication_button, R.id.score_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_work_button:
                break;
            case R.id.up_button:
                if (CloudClass.user.getType() == 1) {
                    Intent upIntent = new Intent(getContext(), UpFileActivity.class);
                    startActivity(upIntent);
                } else {
                    MessageUtils.makeToast("只有老师才能上传资料");
                }
                break;
            case R.id.sign_button:
                break;
            case R.id.communication_button:
                break;
            case R.id.score_button:
                break;
        }
    }
}
