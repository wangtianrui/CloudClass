package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.SignRecordAdapter;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.Sign;
import com.scorpiomiku.cloudclass.bean.StudentSign;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SignRecordActivity extends BaseActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private SignRecordAdapter adapter;
    private ArrayList<Sign> list = new ArrayList<>();
    private AlertDialog.Builder normalDialog;

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
                    case 1000:
                        MessageUtils.makeToast("发起成功");
                        refreshData();
                        break;
                    case 1001:
                        MessageUtils.makeToast("发起失败");
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
        adapter = new SignRecordAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        refreshData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_record;
    }

    @Override
    public void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("courseId", CloudClass.course.getCourse_id());
        WebUtils.getSign(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result > 0) {
                    Gson gson = new Gson();
                    Sign[] signs = gson.fromJson(jsonObject.get("values"), Sign[].class);
                    list.clear();
                    list.addAll(Arrays.asList(signs));
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

    @OnClick({R.id.back_button, R.id.fab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                fileList();
                break;
            case R.id.fab_add:
                showTeacherDialog();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }


    private void showTeacherDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        normalDialog =
                new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        normalDialog.setIcon(R.drawable.ic_sign_in);
        normalDialog.setTitle("发起签到");
        final String code = String.valueOf((new Random()).nextInt()).substring(0, 6).trim();
        normalDialog.setMessage("让同学使用   " + code + "   进行签到\n\n" + "点击确定进行发布");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        HashMap<String, String> data = new HashMap<>();
                        data.put("courseId", CloudClass.course.getCourse_id());
                        data.put("signCode", code);
                        data.put("studentId", CloudClass.user.getPhone());
                        WebUtils.upSign(data, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                JsonObject jsonObject = getJsonObj(response);
                                if (jsonObject.get("result").getAsInt() == 1) {
                                    handler.sendEmptyMessage(1000);
                                } else {
                                    handler.sendEmptyMessage(1001);
                                }
                            }
                        });
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        MessageUtils.makeToast("取消签到");
                    }
                });
        // 显示
        normalDialog.show();
    }
}
