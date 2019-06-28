package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Answer;

import java.util.List;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerHolder> {
    private List<Answer> list;
    private Context context;

    public AnswerAdapter(List<Answer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_answer, parent, false);
        return new AnswerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
