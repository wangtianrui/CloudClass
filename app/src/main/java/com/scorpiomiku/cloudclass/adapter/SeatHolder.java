package com.scorpiomiku.cloudclass.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.SeatItem;
import com.scorpiomiku.cloudclass.bean.User;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
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
 * Created by ScorpioMiku on 2019/6/29.
 */

public class SeatHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.seat)
    ImageView seat;
    private View itemView;
    private User user;

    private ImageView avatar;
    private TextView name;
    private TextView idCard;
    private TextView phone;

    private Handler handler;

    @SuppressLint("HandlerLeak")
    public SeatHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Glide.with(itemView.getContext()).load(user.getAvatar()).into(avatar);
                        name.setText(user.getName());
                        idCard.setText(user.getId_card());
                        phone.setText(user.getPhone());
                        break;
                }
            }
        };
    }

    public void bindView(SeatItem seatItem, int position, int row, int col) {
        int now_row = position / col;
        int now_col = position % col;

        if (seatItem.getCol() == now_col && seatItem.getRow() == now_row) {
            Glide.with(itemView.getContext()).load(R.drawable.ic_seat_red).into(seat);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CloudClass.user.getType() == 1) {
                    View v = LayoutInflater.from(itemView.getContext()).inflate(R.layout.seat_dialog, null);
                    final AlertDialog dialog = new AlertDialog.Builder(itemView.getContext()).setView(v).create();
                    avatar = v.findViewById(R.id.avatar);
                    name = v.findViewById(R.id.name);
                    phone = v.findViewById(R.id.phone);
                    idCard = v.findViewById(R.id.id_card);
                    dialog.show();

                    HashMap<String, String> data = new HashMap<>();
                    data.put("userId", seatItem.getStudentId());
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
                                handler.sendEmptyMessage(1);
                            }
                        }
                    });
                }
            }
        });
    }

    protected JsonObject getJsonObj(Response response) throws IOException {
        String result = response.body().string();
        MessageUtils.logd(result);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;
    }
}
