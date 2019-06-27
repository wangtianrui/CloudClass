package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.CommonUtils;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;
import com.scorpiomiku.cloudclass.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentDetailActivity extends BaseActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.llUserBusinessCardContainer)
    LinearLayout llUserBusinessCardContainer;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.llUserTag)
    LinearLayout llUserTag;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.id_card)
    TextView idCard;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.bn_contact_bottom)
    BottomNavigationView bnContactBottom;
    private User user;

    @Override
    protected Handler initHandle() {
        return null;
    }

    @Override
    public void iniview() {
        Intent intent = getIntent();
        this.user = (User) intent.getSerializableExtra("user");
        bnContactBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        name.setText(user.getName());
        phone.setText(user.getPhone());
        idCard.setText(user.getId_card());
        Glide.with(this).load(ConstantUtils.mediaHost + user.getAvatar()).into(avatar);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_student_detail;
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

    @OnClick(R.id.back_button)
    public void onViewClicked() {
        finish();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.contact_sms:
                    CommonUtils.toMessageChat(StudentDetailActivity.this, user.getPhone());
                    return true;
                case R.id.contact_phone:
                    Uri uri = Uri.parse("tel:" + user.getPhone());
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    if (ActivityCompat.checkSelfPermission(StudentDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(StudentDetailActivity.this, "没有权限", Toast.LENGTH_SHORT);
                    }
                    startActivity(intent);
                    return true;


            }
            return false;
        }

    };
}
