package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Course;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassHolder> {
    private Context context;
    private List<Course> mlist;

    public ClassAdapter(Context context, List mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override

    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cloud_class_item, parent, false);
        return new ClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        holder.bindView(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
