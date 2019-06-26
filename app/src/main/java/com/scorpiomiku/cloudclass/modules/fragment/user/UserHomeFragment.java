package com.scorpiomiku.cloudclass.modules.fragment.user;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class UserHomeFragment extends BaseFragment {
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name_text_view)
    TextView nameTextView;
    @BindView(R.id.sex_text_view)
    TextView sexTextView;
    @BindView(R.id.phone_text_view)
    TextView phoneTextView;
    @BindView(R.id.confirm_teacher)
    ImageView confirmTeacher;
    @BindView(R.id.academy_text_view)
    TextView academyTextView;
    @BindView(R.id.class_text_view)
    TextView classTextView;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;
    @BindView(R.id.bt_logout)
    Button btLogout;
    Unbinder unbinder;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.tv_setting_version)
    TextView tvSettingVersion;

    @Override
    protected Handler initHandle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_home_fragment_layout;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void initView() {
        setUI();
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

    @OnClick(R.id.bt_logout)
    public void onViewClicked() {
        getActivity().finish();

    }

    public void setUI() {
        Glide.with(getContext()).load(ConstantUtils.mediaHost + CloudClass.user.getAvatar()).into(avatar);
        nameTextView.setText(CloudClass.user.getName());
        sexTextView.setText(CloudClass.user.getSex());
        phoneTextView.setText(CloudClass.user.getPhone());
        if (CloudClass.user.getType() == 1) {
            Glide.with(getContext()).load(R.drawable.ic_confirm_teacher_red).into(confirmTeacher);
        }
        academyTextView.setText(CloudClass.user.getAcademy());
        classTextView.setText(CloudClass.user.getClass_number());
    }
}
