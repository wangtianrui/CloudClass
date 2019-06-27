package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class AddCourseDetialActivity extends BaseActivity {
    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.ok_button)
    Button okButton;
    @BindView(R.id.et_week)
    EditText etWeek;
    @BindView(R.id.et_section)
    EditText etSection;
    @BindView(R.id.et_span)
    EditText etSpan;
    @BindView(R.id.et_classroom_number)
    EditText etClassroomNumber;
    @BindView(R.id.et_row_number)
    EditText etRowNumber;
    @BindView(R.id.et_column_number)
    EditText etColumnNumber;

    @Override
    protected Handler initHandle() {
        return null;
    }

    @Override
    public void iniview() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_detail;
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

    @OnClick({R.id.back_button, R.id.ok_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.ok_button:
                CreateCourseActivity.section = etSection.getText().toString().trim();
                CreateCourseActivity.span = etSpan.getText().toString().trim();
                CreateCourseActivity.week = etWeek.getText().toString().trim();
                CreateCourseActivity.rowNumber = etRowNumber.getText().toString().trim();
                CreateCourseActivity.columnNumber = etColumnNumber.getText().toString().trim();
                CreateCourseActivity.classroomNumber = etClassroomNumber.getText().toString().trim();
                finish();
                break;
        }
    }
}
