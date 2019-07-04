package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Power;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/7/4.
 */

public class PowerAdapter extends RecyclerView.Adapter<PowerHolder> {
    private List<Power> list;
    private Context context;

    public PowerAdapter(List<Power> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PowerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.power_list_item, parent, false);
        return new PowerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PowerHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
