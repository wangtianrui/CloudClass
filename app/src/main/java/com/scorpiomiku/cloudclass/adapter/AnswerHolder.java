package com.scorpiomiku.cloudclass.adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Answer;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;
import com.scorpiomiku.cloudclass.utils.StringUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;


import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class AnswerHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;
    @BindView(R.id.name_text_view)
    TextView nameTextView;
    @BindView(R.id.score_text_view)
    TextView scoreTextView;
    @BindView(R.id.check_image_view)
    ImageView checkImageView;
    @BindView(R.id.date_text_view)
    TextView dateTextView;
    private View view;
    private Handler handler;
    private User user;

    @SuppressLint("HandlerLeak")
    public AnswerHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        setUI();
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @SuppressLint("SetTextI18n")
    public void bindView(Answer answer) {
        HashMap<String, String> data = new HashMap<>();
        data.put("userId", answer.getUper_id());
        WebUtils.getUser(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result == 1) {
                    Gson gson = new Gson();
                    user = gson.fromJson(jsonObject.get("values"), User.class);
                }
                handler.sendEmptyMessage(result);
            }
        });
        dateTextView.setText(StringUtils.timeFormat(answer.getUp_time()));
        if (answer.getScore() != -1) {
            scoreTextView.setText(answer.getScore() + "");
            checkImageView.setImageResource(R.drawable.ic_checked);
        }
    }

    private void setUI() {
        Glide.with(view.getContext()).load(ConstantUtils.mediaHost + user.getAvatar()).into(avatarImageView);
        nameTextView.setText(user.getName());
    }

    protected JsonObject getJsonObj(Response response) throws IOException {
        String result = response.body().string();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;

    }
}
