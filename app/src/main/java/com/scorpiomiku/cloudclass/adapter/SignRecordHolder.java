package com.scorpiomiku.cloudclass.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Sign;
import com.scorpiomiku.cloudclass.bean.StudentSign;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.SignMemberListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class SignRecordHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.course_name)
    TextView courseName;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.sign_code)
    TextView signCode;
    private View view;

    public SignRecordHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);

    }

    public void bindView(Sign studentSign) {
        courseName.setText(CloudClass.course.getName());
        signCode.setText(studentSign.getSign_code());
        date.setText(studentSign.getUp_time().substring(0, 19).replace("T", " "));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignMemberListActivity.class);
                view.getContext().startActivity(intent);
                CloudClass.signCode = studentSign.getSign_code();
            }
        });
    }
}
