package com.scorpiomiku.cloudclass.modules.activity.cloudclass.homework;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SendHomeWorkActivity extends BaseActivity {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title_edit_text)
    EditText titleEditText;
    @BindView(R.id.content_edit_view)
    EditText contentEditView;
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
                        MessageUtils.makeToast("发布成功");
                        finish();
                        break;
                    case 0:
                        MessageUtils.makeToast("发布失败");
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    public void iniview() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_home_work;
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

    @OnClick({R.id.back_button, R.id.send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.send_button:
                String title = titleEditText.getText().toString().trim();
                String content = contentEditView.getText().toString().trim();
                if (title.equals("") || content.equals("")) {
                    MessageUtils.makeToast("不能为空哟");
                } else {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("courseId", CloudClass.course.getCourse_id());
                    data.put("title", title);
                    data.put("content", content);
                    data.put("userId", CloudClass.user.getPhone());
                    WebUtils.addHomeWork(data, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

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
}
