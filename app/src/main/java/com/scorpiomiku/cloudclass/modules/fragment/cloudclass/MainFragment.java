package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.SourceAdapter;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.MySource;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.CommunicationActivity;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.HomeWorkListActivity;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.SignRecordActivity;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.UpFileActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.home_work_button)
    LinearLayout homeWorkButton;
    @BindView(R.id.up_button)
    LinearLayout upButton;
    @BindView(R.id.sign_button)
    LinearLayout signButton;
    @BindView(R.id.communication_button)
    LinearLayout communicationButton;
    @BindView(R.id.score_button)
    LinearLayout scoreButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ly_root_main)
    LinearLayout lyRootMain;
    Unbinder unbinder;

    private ArrayList<MySource> mySources = new ArrayList<>();
    private SourceAdapter adapter;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        break;
                    case 1002:
                        MessageUtils.makeToast("签到码错误");
                        break;
                    case -1:
                        break;
                    case 1000:
                        MessageUtils.makeToast("签到成功");
                        break;
                    case 1001:
                        MessageUtils.makeToast("你已签到过，不可重复签到");
                        break;
                    default:
                        adapter.notifyDataSetChanged();
                }
            }
        };
        return handler;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cloudclass_main_fragment;
    }

    @Override
    protected void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("courseId", CloudClass.course.getCourse_id());
        WebUtils.getSource(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result > 0) {
                    Gson gson = new Gson();
                    MySource[] sources = gson.fromJson(jsonObject.get("values"), MySource[].class);
                    mySources.clear();
                    mySources.addAll(Arrays.asList(sources));
                }
                handler.sendEmptyMessage(result);
            }
        });
    }

    @Override
    protected void initView() {
        adapter = new SourceAdapter(mySources, getContext());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.home_work_button, R.id.up_button, R.id.sign_button, R.id.communication_button, R.id.score_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_work_button:
                Intent i1 = new Intent(getContext(), HomeWorkListActivity.class);
                startActivity(i1);
                break;
            case R.id.up_button:
                if (CloudClass.user.getType() == 1) {
                    Intent upIntent = new Intent(getContext(), UpFileActivity.class);
                    startActivity(upIntent);
                } else {
                    MessageUtils.makeToast("只有老师才能上传资料");
                }
                break;
            case R.id.sign_button:
                if (CloudClass.user.getType() == 1) {
                    Intent i = new Intent(getContext(), SignRecordActivity.class);
                    startActivity(i);
                } else {
                    showInputDialog();
                }
                break;
            case R.id.communication_button:
                Intent intent = new Intent(getContext(), CommunicationActivity.class);
                startActivity(intent);
                break;
            case R.id.score_button:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @SuppressLint("ResourceAsColor")
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(getContext());
        editText.setTextColor(R.color.black);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
        inputDialog.setTitle("请输入签到码\n\n").setView(editText);
        inputDialog.setPositiveButton("签到",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String code = editText.getText().toString().trim();
                        HashMap<String, String> data = new HashMap<>();
                        data.put("signCode", code);
                        data.put("studentId", CloudClass.user.getPhone());
                        WebUtils.studentSign(data, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                JsonObject jsonObject = getJsonObj(response);
                                int result = jsonObject.get("result").getAsInt();
                                if (result == 0) {
                                    handler.sendEmptyMessage(1001);
                                } else if (result == 1) {
                                    handler.sendEmptyMessage(1000);
                                } else {
                                    handler.sendEmptyMessage(1002);
                                }
                            }
                        });
                    }
                }).show();
    }
}
