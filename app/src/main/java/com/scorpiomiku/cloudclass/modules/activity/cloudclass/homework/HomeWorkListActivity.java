package com.scorpiomiku.cloudclass.modules.activity.cloudclass.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.HomeWorkAdapter;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.HomeWork;
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

public class HomeWorkListActivity extends BaseActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.send_new_home_work)
    Button sendNewHomeWork;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ArrayList<HomeWork> list = new ArrayList<>();
    private HomeWorkAdapter adapter;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
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
        adapter = new HomeWorkAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        refreshData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_work_list;
    }

    @Override
    public void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("courseId", CloudClass.course.getCourse_id());
        WebUtils.getHomeWork(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result > 0) {
                    Gson gson = new Gson();
                    HomeWork[] homeWork = gson.fromJson(jsonObject.get("values"), HomeWork[].class);
                    list.clear();
                    list.addAll(Arrays.asList(homeWork));
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

    @OnClick({R.id.back_button, R.id.send_new_home_work})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.send_new_home_work:
                Intent intent = new Intent(this, SendHomeWorkActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }
}
