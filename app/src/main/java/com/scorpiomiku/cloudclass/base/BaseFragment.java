package com.scorpiomiku.cloudclass.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.utils.MessageUtils;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public abstract class BaseFragment extends Fragment {
    private View myView;
    protected Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.myView = inflater.inflate(getLayoutId(), container, false);
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        handler = initHandle();
        refreshData();
    }

    protected abstract Handler initHandle();


    protected abstract int getLayoutId();

    protected abstract void refreshData();

    protected abstract void initView();

    public View getMyView() {
        return myView;
    }

    protected JsonObject getJsonObj(Response response) throws IOException {
        String result = response.body().string();
        MessageUtils.logd(result);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;
    }
}
