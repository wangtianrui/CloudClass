package com.scorpiomiku.cloudclass.modules.activity.cloudclass.homework;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandInHomeWorkActivity extends BaseActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.question_edit_text)
    EditText questionEditText;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.send_button)
    Button sendButton;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        MessageUtils.makeToast("提交成功");
                        finish();
                        break;
                    case 0:
                        MessageUtils.makeToast("提交作业失败");
                        break;
                }
            }
        };
        return null;
    }

    @Override
    public void iniview() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hand_in_home_work;
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

    @OnClick({R.id.back_button, R.id.image_view, R.id.send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.image_view:
                break;
            case R.id.send_button:

                break;
        }
    }
}
