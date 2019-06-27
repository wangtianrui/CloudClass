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

public class SignedStudentAdapter extends RecyclerView.Adapter<SignedStudentHolder> {
    private Context context;
    private List<User> list;

    public SignedStudentAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SignedStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_member, parent, false);
        return new SignedStudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SignedStudentHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
