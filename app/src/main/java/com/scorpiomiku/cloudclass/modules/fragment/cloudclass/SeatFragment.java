package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.SeatAdapter;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.SeatItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    private ArrayList<SeatItem> list = new ArrayList<>();
    private SeatAdapter seatAdapter;
    private SeatItem seatItem;

    @Override
    protected Handler initHandle() {
        return null;
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
        } else {
            seatItem = new SeatItem();
            seatItem.setCol(3);
            seatItem.setRow(5);
            title.setText("上课教室为:" + CloudClass.course.getClass_room_number()
                    + "您的座位号为:" + seatItem.getRow() + "行" + seatItem.getCol() + "列");
            sortButton.setVisibility(View.INVISIBLE);

            for (int i = 0; i < row * col; i++) {
                list.add(seatItem);
            }
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

    @OnClick(R.id.sort_button)
    public void onViewClicked() {
    }
}
