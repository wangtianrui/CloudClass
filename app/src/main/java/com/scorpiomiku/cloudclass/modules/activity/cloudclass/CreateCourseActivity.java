package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateCourseActivity extends BaseActivity {


    @BindView(R.id.iv_class_cover)
    ImageView ivClassCover;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.et_class_name)
    EditText etClassName;
    @BindView(R.id.et_course_name)
    EditText etCourseName;
    @BindView(R.id.ly_course_name)
    LinearLayout lyCourseName;
    @BindView(R.id.et_textbook_set)
    EditText etTextbookSet;
    @BindView(R.id.ly_class_textbook)
    LinearLayout lyClassTextbook;
    @BindView(R.id.tv_classdetail_set)
    TextView tvClassdetailSet;
    @BindView(R.id.ly_class_detail)
    LinearLayout lyClassDetail;
    @BindView(R.id.bt_create_class)
    Button btCreateClass;
    @BindView(R.id.ly_root_main)
    LinearLayout lyRootMain;
    public static String span = "";
    public static String week = "";
    public static String section = "";
    public static String rowNumber = "";
    public static String columnNumber = "";
    public static String classroomNumber = "";
    private static final String TAG = "CreateCourseActivity";

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        finish();
                        break;
                    case 0:
                        MessageUtils.makeToast("提交失败");
                        break;
                    case -1:
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    public void iniview() {
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.class_logo_default).into(ivClassCover);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_course;
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

    @OnClick({R.id.ly_class_detail, R.id.bt_create_class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_class_detail:
                Intent intent = new Intent(CreateCourseActivity.this, AddCourseDetialActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_create_class:
                String name = etClassName.getText().toString().trim();
                String courseId = String.valueOf((new Random()).nextInt()).substring(0, 5)
                        + CloudClass.user.getPhone().substring(0, 5);
                String inviteCode = String.valueOf((new Random()).nextInt()).substring(1, 6);
                String creatorId = CloudClass.user.getPhone();
                if (classroomNumber.equals("")) {
                    MessageUtils.makeToast("请填写课程详细后再保存");
                } else {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("courseId", courseId);
                    data.put("inviteCode", inviteCode);
                    data.put("creatorId", creatorId);
                    data.put("rowNumber", rowNumber);
                    data.put("columnNumber", columnNumber);
                    data.put("classroomNumber", classroomNumber);
                    WebUtils.createCourse(data, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            JsonObject jsonObject = getJsonObj(response);
                            handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!classroomNumber.equals("")) {
            tvClassdetailSet.setText("已设置");
        }
    }
}
