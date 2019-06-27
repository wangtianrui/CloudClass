package com.scorpiomiku.cloudclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Sign;
import com.scorpiomiku.cloudclass.bean.StudentSign;

import java.util.ArrayList;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class SignRecordAdapter extends RecyclerView.Adapter<SignRecordHolder> {
    private Context context;
    private ArrayList<Sign> list;

    public SignRecordAdapter(Context context, ArrayList<Sign> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SignRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sign_record, parent, false);
        return new SignRecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SignRecordHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
