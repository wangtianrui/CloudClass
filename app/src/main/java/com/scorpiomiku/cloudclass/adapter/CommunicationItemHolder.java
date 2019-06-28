package com.scorpiomiku.cloudclass.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.CommunicationItem;
import com.scorpiomiku.cloudclass.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class CommunicationItemHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;
    @BindView(R.id.uper_name)
    TextView uperName;
    @BindView(R.id.temp)
    LinearLayout temp;
    private View view;

    public CommunicationItemHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bindView(CommunicationItem communicationItem) {
        content.setText(communicationItem.getContent());
        date.setText(StringUtils.timeFormat(communicationItem.getUp_time()));
        if (communicationItem.getUser_type() == 1) {
            avatarImageView.setImageResource(R.drawable.ic_teacher);
        } else {
            avatarImageView.setImageResource(R.drawable.ic_student);
        }
        uperName.setText(communicationItem.getUser_name());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CloudClass.user.getType() == 1) {
                    showInputDialog();
                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(itemView.getContext());
        editText.setTextColor(R.color.black);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
        inputDialog.setTitle("输入成绩(请勿输入非数字内容)\n\n").setView(editText);
        inputDialog.setPositiveButton("提交",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String code = editText.getText().toString().trim();


                    }
                }).show();
    }
}
