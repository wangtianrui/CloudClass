package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.User;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class MemberAdapter extends RecyclerView.Adapter<MemberHolder> {
    private List<User> list;
    private Context context;

    public MemberAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_member, parent, false);
        return new MemberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
