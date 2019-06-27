package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Inform;

import java.util.List;


/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class InformationAdapter extends RecyclerView.Adapter<InformationHolder> {
    private List<Inform> list;
    private Context context;

    public InformationAdapter(List<Inform> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public InformationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_information, parent, false);
        return new InformationHolder(view);
    }

    @Override
    public void onBindViewHolder(InformationHolder holder, int position) {
        holder.bindView(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
