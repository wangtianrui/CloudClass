package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.CommunicationItem;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class CommunicationItemAdapter extends RecyclerView.Adapter<CommunicationItemHolder> {
    private Context context;
    private List<CommunicationItem> communicationItems;

    public CommunicationItemAdapter(Context context, List<CommunicationItem> communicationItems) {
        this.context = context;
        this.communicationItems = communicationItems;
    }

    @NonNull
    @Override
    public CommunicationItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_communication_detail, parent, false);
        return new CommunicationItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunicationItemHolder holder, int position) {
        holder.bindView(communicationItems.get(position));
    }

    @Override
    public int getItemCount() {
        return communicationItems.size();
    }
}
