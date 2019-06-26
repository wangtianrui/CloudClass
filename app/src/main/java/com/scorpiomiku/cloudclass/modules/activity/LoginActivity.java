package com.scorpiomiku.cloudclass.modules.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_edt_username)
    EditText loginEdtUsername;
    @BindView(R.id.login_edt_password)
    EditText loginEdtPassword;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.linear_layout_btn_register)
    LinearLayout linearLayoutBtnRegister;
    @BindView(R.id.login_find_pwd)
    TextView loginFindPwd;

    private static final String TAG = "LoginActivity";

    @OnClick({R.id.login_btn_login, R.id.linear_layout_btn_register, R.id.login_find_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:
                String phone = loginEdtUsername.getText().toString().trim();
                String password = loginEdtPassword.getText().toString().trim();
                HashMap<String, String> data = new HashMap<>();
                data.put("phone", phone);
                data.put("passWord", password);
                WebUtils.login(data, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        JsonObject jsonObject = getJsonObj(response);
                        User user = gson.fromJson(jsonObject.get("values"), User.class);
                        CloudClass.user = user;
                        Log.d(TAG, "onResponse: login ok" + user);
                        handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
                    }
                });
                break;
            case R.id.linear_layout_btn_register:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_find_pwd:
                break;
        }
    }

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 0:
                        MessageUtils.makeToast("密码错误");
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
        setTitle("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void refreshData() {

    }


}
