package com.scorpiomiku.cloudclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("");

    }

    @OnClick({R.id.login_btn_login, R.id.linear_layout_btn_register, R.id.login_find_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:
                break;
            case R.id.linear_layout_btn_register:
                break;
            case R.id.login_find_pwd:
                break;
        }
    }
}
