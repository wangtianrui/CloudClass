package com.scorpiomiku.cloudclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class SignedStudentHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.id_card)
    TextView idCard;
    private View view;

    public SignedStudentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        view = itemView;
    }

    public void bindView(User user) {
        Glide.with(view.getContext()).load(ConstantUtils.mediaHost + user.getAvatar()).into(avatar);
        name.setText(user.getName());
        idCard.setText(user.getId_card());
    }
}
