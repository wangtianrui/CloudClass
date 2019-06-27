package com.scorpiomiku.cloudclass.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Inform;
import com.scorpiomiku.cloudclass.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */


public class InformationHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.index_title)
    TextView indexTitle;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.date_text)
    TextView dateText;
    private View itemView;
    private Inform inform;

    public InformationHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void bindView(final Inform inform, int position) {
        this.inform = inform;
        indexTitle.setText("通知  " + position);
        titleText.setText(inform.getTitle());
        dateText.setText(StringUtils.timeFormat(inform.getUp_time()));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.inform_dialog, null);

                final AlertDialog dialog = new AlertDialog.Builder(itemView.getContext()).setView(view).create();
                TextView title = view.findViewById(R.id.title_text);
                TextView content = view.findViewById(R.id.content_text);
                TextView date = view.findViewById(R.id.date_text);
                title.setText(inform.getTitle());
                date.setText(StringUtils.timeFormat(inform.getUp_time()));
                content.setText("  " + inform.getContent());
                dialog.show();
            }
        });
    }


}
