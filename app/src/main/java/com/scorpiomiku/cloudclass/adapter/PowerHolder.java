package com.scorpiomiku.cloudclass.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.Power;
import com.scorpiomiku.cloudclass.utils.ChartMaker;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/7/4.
 */

public class PowerHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.id_card)
    TextView idCard;
    @BindView(R.id.chart)
    LineChart chart;
    @BindView(R.id.container)
    LinearLayout container;
    private Power power;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    public PowerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        MessageUtils.makeToast("打分成功");
                }
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void bindView(Power power) {
        this.power = power;
        name.setText(power.getStudent_name());
        status.setText(power.getStatus());
        idCard.setText(power.getId_card());
        String[] levels = power.getLevel().split(";");
        ArrayList<Entry> pointValues = new ArrayList<>();
        pointValues.add(new Entry(0, 100));
        ArrayList<Float> data = new ArrayList<>();
        int i;
        for (i = 1; i <= levels.length - 1; i++) {
            pointValues.add(new Entry(i, Float.valueOf(levels[i])));
            data.add(Float.valueOf(levels[i]));
        }
        if (analysis(data)) {
            container.setBackgroundColor(itemView.getContext().getColor(R.color.red));
        }
        pointValues.add(new Entry(i, 0));
        ChartMaker.initSingleLineChart(chart, pointValues, "电量情况");
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
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
                        Boolean number = true;
                        if (code == null || "".equals(code)) {
                            number = false;
                        }
                        number = Pattern.matches("-?[0-9]*(\\.?)[0-9]*", code);
                        if (number) {
                            HashMap<String, String> data = new HashMap<>();
                            data.put("score", code);
                            data.put("courseId", CloudClass.course.getCourse_id());
                            data.put("userId", power.getStudent_id());
                            data.put("id", "power-" + power.getId());
                            WebUtils.addScore(data, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    JsonObject jsonObject = getJsonObj(response);
                                    handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
                                }
                            });
                        } else {
                            MessageUtils.makeToast("请勿输入非数字");
                        }

                    }
                }).

                show();

    }

    protected JsonObject getJsonObj(Response response) throws IOException {
        String result = response.body().string();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;

    }

    private boolean analysis(ArrayList<Float> data) {
        float last = data.get(1);
        boolean game = false;
        for (int i = 1; i < data.size(); i += 20) {
            float now = data.get(i);
            if (now - last > 5) {
                game = true;
            }
            last = now;
        }
        return game;
    }

}
