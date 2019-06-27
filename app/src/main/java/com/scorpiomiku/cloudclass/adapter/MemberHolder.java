package com.scorpiomiku.cloudclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class MemberHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.bow)
    ImageView bow;
    private View view;

    public MemberHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bindView(User user) {
        Glide.with(view.getContext()).load(user.getAvatar()).into(avatar);
        name.setText(user.getName());
        phone.setText(user.getId_card());
    }
}
