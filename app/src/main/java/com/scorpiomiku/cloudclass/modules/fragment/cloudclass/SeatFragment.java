package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.SeatAdapter;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.SeatItem;
import com.scorpiomiku.cloudclass.modules.activity.cloudclass.power.PowerActivity;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

public class SeatFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.sort_button)
    Button sortButton;
    @BindView(R.id.power_button)
    Button powerButton;
    @BindView(R.id.plat)
    TextView plat;
    private ArrayList<SeatItem> list = new ArrayList<>();
    private SeatAdapter seatAdapter;
    private SeatItem seatItem;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @SuppressLint("SetTextI18n")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 2001:
                        seatAdapter.notifyDataSetChanged();
                        title.setText("上课教室为:" + CloudClass.course.getClass_room_number()
                                + "您的座位号为:" + seatItem.getRow() + "行" + seatItem.getCol() + "列");
                        break;
                    case 2000:
                        title.setText("上课教室为:" + CloudClass.course.getClass_room_number()
                                + "未找到您的座位");
                        break;
                    case 1:
                        MessageUtils.makeToast("已为学生自动排座，请让学生按各自手机显示就位");
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cloudclass_seat_fragment;
    }

    @Override
    protected void refreshData() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        int col = CloudClass.course.getColumn_number();
        int row = CloudClass.course.getRow_number();
        seatAdapter = new SeatAdapter(list, row, col, getContext());
        recyclerView.setAdapter(seatAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), col));
        if (CloudClass.user.getType() == 1) {
            title.setText("上课教室为:" + CloudClass.course.getClass_room_number());
            plat.setVisibility(View.INVISIBLE);
        } else {
            HashMap<String, String> data = new HashMap<>();
            data.put("studentId", CloudClass.user.getPhone());
            data.put("courseId", CloudClass.course.getCourse_id());
            WebUtils.getSeat(data, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    JsonObject jsonObject = getJsonObj(response);
                    int result = jsonObject.get("result").getAsInt();
                    if (result == 1) {
                        Gson gson = new Gson();
                        SeatItem seat = gson.fromJson(jsonObject.get("values"), SeatItem.class);
                        seatItem = seat;
                        list.clear();
                        for (int i = 0; i < row * col; i++) {
                            list.add(seat);
                        }
                        handler.sendEmptyMessage(2001);
                    } else {
                        handler.sendEmptyMessage(2000);
                    }


                }
            });

            sortButton.setVisibility(View.INVISIBLE);
            powerButton.setVisibility(View.INVISIBLE);

        }

        seatAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.sort_button, R.id.power_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sort_button:
                HashMap<String, String> data = new HashMap<>();
                data.put("courseId", CloudClass.course.getCourse_id());
                WebUtils.autoSeat(data, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JsonObject jsonObject = getJsonObj(response);
                        handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
                    }
                });
                break;
            case R.id.power_button:
                Intent intent = new Intent(getActivity(), PowerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
