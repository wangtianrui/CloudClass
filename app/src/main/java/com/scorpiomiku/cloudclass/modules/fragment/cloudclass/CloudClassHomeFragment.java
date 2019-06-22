package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClassHomeFragment extends BaseFragment {
    public static CloudClassHomeFragment getInstance() {
        return new CloudClassHomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cloudclass_home_fragment_layout;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void initView() {

    }
}
