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

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.ClassAdapter;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.Course;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.CreateCourseActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClassHomeFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.fab)
    FloatingActionsMenu fab;
    private ClassAdapter adapter;
    private List<Course> mlist = new ArrayList<>();

    public static CloudClassHomeFragment getInstance() {
        return new CloudClassHomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cloudclass_home_fragment_layout;
    }

    @Override
    protected void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("userId", CloudClass.user.getPhone());
        WebUtils.getCourseList(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result != 0) {
                    Gson gson = new Gson();
                    Course[] courses = gson.fromJson(jsonObject.get("values"), Course[].class);
                    mlist.clear();
                    mlist.addAll(Arrays.asList(courses));
                }

                handler.sendEmptyMessage(result);
            }
        });
    }

    @Override
    protected void initView() {
        adapter = new ClassAdapter(getContext(), mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        initFloatButton();
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
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1056:
                        refreshData();
                        break;
                    case 1000:
                        MessageUtils.makeToast("邀请码错误");
                        break;
                    case 0:
                        MessageUtils.makeToast("查无课程");
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 初始化悬浮按钮
     */
    private void initFloatButton() {
        String message = "";
        if (CloudClass.user.getType() == 1) {
            message = "点击这里可以创建新的课堂";
        } else {
            message = "点击这里可以根据老师给予的邀请码加入课堂";
        }
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.fab)
                .setPrimaryText("课堂操作按钮")
                .setSecondaryText(message)
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                            // User has pressed the prompt target
                        }
                    }
                })
                .show();

        FloatingActionButton actionButtonActivate = new FloatingActionButton(getContext());
        actionButtonActivate.setIcon(R.drawable.ic_join);
        actionButtonActivate.setTextDirection(FloatingActionButton.TEXT_DIRECTION_FIRST_STRONG_LTR);
        actionButtonActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CloudClass.user.getType() == 1) {
                    Intent intent = new Intent(getActivity(), CreateCourseActivity.class);
                    startActivity(intent);
                } else {
                    final EditText inputServer = new EditText(getContext());
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("输入邀请码").setIcon(R.drawable.ic_search).setView(inputServer)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String text = inputServer.getText().toString();
                            HashMap<String, String> data = new HashMap<>();
                            data.put("inviteCode", text);
                            data.put("studentId", CloudClass.user.getPhone());
                            WebUtils.joinCourseByInvited(data, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    JsonObject jsonObject = getJsonObj(response);
                                    if (jsonObject.get("result").getAsInt() > 0) {
                                        handler.sendEmptyMessage(1056);
                                    } else {
                                        handler.sendEmptyMessage(1000);
                                    }
                                }
                            });
                        }
                    });
                    builder.show();
                }
            }
        });
        fab.addButton(actionButtonActivate);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }
}
