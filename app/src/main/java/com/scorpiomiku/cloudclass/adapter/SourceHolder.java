package com.scorpiomiku.cloudclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.MySource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class SourceHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.file_name)
    TextView fileName;
    @BindView(R.id.uper_name_text_view)
    TextView uperNameTextView;
    @BindView(R.id.load_progress_bar)
    ProgressBar loadProgressBar;
    @BindView(R.id.cover)
    LinearLayout cover;
    @BindView(R.id.whole_view)
    RelativeLayout wholeView;
    private View view;

    public SourceHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bindView(MySource mySource) {
        switch (mySource.getType()) {
            case 0:
                //ppt
                imageView.setImageResource(R.drawable.ppt);
                break;
            case 1:
                //doc
                imageView.setImageResource(R.drawable.word);
                break;
            case 2:
                //xls
                imageView.setImageResource(R.drawable.xls);
                break;
            case 3:
                //pdf
                imageView.setImageResource(R.drawable.pdf);
                break;
            case 4:
                //txt
                imageView.setImageResource(R.drawable.txt);
                break;
            default:
                imageView.setImageResource(R.drawable.other);
                break;
        }
        fileName.setText(mySource.getSource_name());
        uperNameTextView.setText(mySource.getUper_name());

    }

    @OnClick(R.id.whole_view)
    public void onViewClicked() {
    }
}
