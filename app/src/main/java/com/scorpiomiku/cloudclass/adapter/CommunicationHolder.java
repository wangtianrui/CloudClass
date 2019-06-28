package com.scorpiomiku.cloudclass.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Communication;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.CommunicationDetailActivity;
import com.scorpiomiku.cloudclass.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class CommunicationHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;
    @BindView(R.id.uper_name)
    TextView uperName;
    private View itemView;

    public CommunicationHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Communication communication) {
        title.setText(communication.getTitle());
        content.setText(communication.getContent());
        date.setText(StringUtils.timeFormat(communication.getUp_time()));
        if (communication.getUser_type() == 1) {
            avatarImageView.setImageResource(R.drawable.ic_teacher);
        } else {
            avatarImageView.setImageResource(R.drawable.ic_student);
        }
        uperName.setText(communication.getUser_name());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), CommunicationDetailActivity.class);
                intent.putExtra("communication", communication);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
