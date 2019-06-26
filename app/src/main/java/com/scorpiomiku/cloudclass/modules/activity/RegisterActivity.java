package com.scorpiomiku.cloudclass.modules.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.id_card)
    EditText idCard;
    @BindView(R.id.teacher_checkbox)
    CheckBox teacherCheckbox;
    @BindView(R.id.register_button)
    Button registerButton;

    private static final String TAG = "RegisterActivity";

    @SuppressLint("HandlerLeak")
    @Override
    protected Handler initHandle() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        // 注册成功
                        finish();
                        break;
                    case 0:
                        //注册失败
                        MessageUtils.makeToast("该账号已被注册");
                        break;
                    case -1:
                        Log.d(TAG, "handleMessage: 注册功能有问题");
                }
            }
        };
        return handler;
    }

    @Override
    public void iniview() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void refreshData() {

    }

    @OnClick(R.id.register_button)
    public void onViewClicked() {
        String phoneNumber = phone.getText().toString().trim();
        String passWord = password.getText().toString().trim();
        String id = idCard.getText().toString().trim();
        String type;
        if (teacherCheckbox.isChecked()) {
            type = "1";
        } else {
            type = "0";
        }
        HashMap<String, String> data = new HashMap<>();
        data.put("phone", phoneNumber);
        data.put("type", type);
        data.put("passWord", passWord);
        data.put("idCard", id);
        WebUtils.rigester(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String temp = response.body().string();
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(temp);
                handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
            }
        });
    }
}
