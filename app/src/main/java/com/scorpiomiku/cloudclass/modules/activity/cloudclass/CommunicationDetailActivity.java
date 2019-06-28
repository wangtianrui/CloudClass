package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.Communication;
import com.scorpiomiku.cloudclass.bean.CommunicationItem;
import com.scorpiomiku.cloudclass.utils.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommunicationDetailActivity extends BaseActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;
    @BindView(R.id.uper_name)
    TextView uperName;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.input_edit_view)
    EditText inputEditView;
    @BindView(R.id.send_button)
    Button sendButton;

    private ArrayList<CommunicationItem> list = new ArrayList<>();
    private Communication communication;

    @Override
    protected Handler initHandle() {
        return null;
    }

    @Override
    public void iniview() {
        Intent intent = getIntent();
        communication = (Communication) intent.getSerializableExtra("communication");
        title.setText(communication.getTitle());
        content.setText(communication.getContent());
        date.setText(StringUtils.timeFormat(communication.getUp_time()));
        if (communication.getUser_type() == 1) {
            avatarImageView.setImageResource(R.drawable.ic_teacher);
        } else {
            avatarImageView.setImageResource(R.drawable.ic_student);
        }
        uperName.setText(communication.getUser_name());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_communication_detail;
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
                break;
        }
    }
}
