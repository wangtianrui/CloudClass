package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.HomeWork;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkHolder> {
    private List<HomeWork> list;
    private Context context;

    public HomeWorkAdapter(List<HomeWork> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeWorkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_work, parent, false);
        return new HomeWorkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
