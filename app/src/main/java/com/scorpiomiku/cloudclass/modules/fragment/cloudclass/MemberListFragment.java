package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.MemberAdapter;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class MemberListFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private MemberAdapter adapter;
    private ArrayList<User> list = new ArrayList<>();

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        MessageUtils.makeToast("查无同学");
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
    protected int getLayoutId() {
        return R.layout.cloudclass_memberlist_fragment;
    }

    @Override
    protected void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("courseId", CloudClass.course.getCourse_id());
        WebUtils.getMember(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result > 0) {
                    Gson gson = new Gson();
                    User[] users = gson.fromJson(jsonObject.get("values"), User[].class);
                    list.clear();
                    list.addAll(Arrays.asList(users));
                }
                handler.sendEmptyMessage(result);
            }
        });
    }

    @Override
    protected void initView() {
        adapter = new MemberAdapter(list, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
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

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }
}
