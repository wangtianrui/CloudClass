package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.User;

import java.util.List;


/**
 * Created by ScorpioMiku on 2019/6/28.
 */
public class StudentScoreAdapter extends RecyclerView.Adapter<StudentScoreHolder> {
    private List<User> list;
    private Context context;

    public StudentScoreAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public StudentScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_score, parent, false);
        return new StudentScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentScoreHolder holder, int position) {
        holder.bindView(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
