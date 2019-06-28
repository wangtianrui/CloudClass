package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Communication;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class CommunicationAdapter extends RecyclerView.Adapter<CommunicationHolder> {
    private Context context;
    private List<Communication> list;

    public CommunicationAdapter(Context context, List<Communication> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommunicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_communication, parent, false);
        return new CommunicationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunicationHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
