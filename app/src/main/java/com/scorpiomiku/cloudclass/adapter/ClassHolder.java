package com.scorpiomiku.cloudclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Course;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class ClassHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.invite_code_text_view)
    TextView inviteCodeTextView;
    @BindView(R.id.class_name)
    TextView className;
    @BindView(R.id.teacher_name)
    TextView teacherName;
    @BindView(R.id.whole_view)
    LinearLayout wholeView;
    private View view;

    public ClassHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, itemView);

    }

    public void bindView(Course course) {
        className.setText(course.getCourseName());
        teacherName.setText(course.getTeacherNumber());
        inviteCodeTextView.setText(course.getInviteCode());
    }

    @OnClick(R.id.whole_view)
    public void onViewClicked() {

    }
}
