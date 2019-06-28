package com.scorpiomiku.cloudclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.HomeWork;
import com.scorpiomiku.cloudclass.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class HomeWorkHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.date_text)
    TextView dateText;
    private View view;

    public HomeWorkHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);

    }

    public void bindView(HomeWork homeWork) {
        title.setText(homeWork.getTitle());
        content.setText(homeWork.getContent());
        dateText.setText(StringUtils.timeFormat(homeWork.getUp_time()));
    }
}
