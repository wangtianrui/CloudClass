package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.SeatItem;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/29.
 */

public class SeatAdapter extends RecyclerView.Adapter<SeatHolder> {
    private List<SeatItem> list;
    private int row;
    private int col;
    private Context context;

    public SeatAdapter(List<SeatItem> list, int row, int col, Context context) {
        this.list = list;
        this.row = row;
        this.col = col;
        this.context = context;
    }

    @NonNull
    @Override
    public SeatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seat, parent, false);
        return new SeatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatHolder holder, int position) {
        holder.bindView(list.get(position), position, row, col);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
