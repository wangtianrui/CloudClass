package com.scorpiomiku.cloudclass.adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;
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


public class StudentScoreHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.index_text_view)
    TextView indexTextView;
    @BindView(R.id.avatar_image_view)
    ImageView avatarImageView;
    @BindView(R.id.name_text_view)
    TextView nameTextView;
    @BindView(R.id.score_text_view)
    TextView scoreTextView;
    @BindView(R.id.linear_container)
    LinearLayout container;
    private View itemView;
    private Handler handler;
    private float score;

    @SuppressLint("HandlerLeak")
    public StudentScoreHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        scoreTextView.setText(score + "");
                        if (score < 60) {
                            container.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.red));
                        }
                        break;

                }
            }
        };
    }

    public void bindView(User user, int position) {
        indexTextView.setText((position + 1) + "");
        Glide.with(itemView.getContext()).load(ConstantUtils.mediaHost + user.getAvatar()).into(avatarImageView);
        nameTextView.setText(user.getName());
        HashMap<String, String> data = new HashMap<>();
        data.put("studentId", user.getPhone());
        WebUtils.getMeanScore(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                score = jsonObject.get("values").getAsFloat();
                handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
            }
        });
    }

    private JsonObject getJsonObj(Response response) throws IOException {
        String result = response.body().string();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;

    }
}
