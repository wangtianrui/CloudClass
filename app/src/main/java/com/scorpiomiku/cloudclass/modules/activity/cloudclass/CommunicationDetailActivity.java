package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.CommunicationItemAdapter;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.Communication;
import com.scorpiomiku.cloudclass.bean.CommunicationItem;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.StringUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private CommunicationItemAdapter adapter;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1000:
                        inputEditView.setText("");
                        refreshData();
                        break;
                    case 1001:
                        MessageUtils.makeToast("发送失败");
                        break;
                    case 0:
                        break;
                    case -1:
                        break;
                    default:
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        };
        return handler;
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
        adapter = new CommunicationItemAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_communication_detail;
    }

    @Override
    public void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("communication_id", communication.getId());
        WebUtils.getCommunicationItem(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result > 0) {
                    Gson gson = new Gson();
                    CommunicationItem[] communicationItems = gson.fromJson(jsonObject.get("values"), CommunicationItem[].class);
                    list.clear();
                    list.addAll(Arrays.asList(communicationItems));
                }
                handler.sendEmptyMessage(result);
            }
        });
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
                String text = inputEditView.getText().toString().trim();
                if (text.equals("")) {
                    MessageUtils.makeToast("不能发表空文本哟");
                } else {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("uperId", CloudClass.user.getPhone());
                    data.put("communication_id", communication.getId());
                    data.put("content", text);
                    WebUtils.sendCommunicationItem(data, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            JsonObject jsonObject = getJsonObj(response);
                            int result = jsonObject.get("result").getAsInt();
                            if (result == 1) {
                                handler.sendEmptyMessage(1000);
                            } else {
                                handler.sendEmptyMessage(1001);
                            }
                        }
                    });
                }
                break;
        }
    }
}
