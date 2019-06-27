package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.MySource;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceHolder> {
    private List<MySource> mySources;
    private Context context;

    public SourceAdapter(List<MySource> mySources, Context context) {
        this.mySources = mySources;
        this.context = context;
    }

    @NonNull
    @Override
    public SourceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_source, parent, false);
        return new SourceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceHolder holder, int position) {
        holder.bindView(mySources.get(position));
    }

    @Override
    public int getItemCount() {
        return mySources.size();
    }
}
