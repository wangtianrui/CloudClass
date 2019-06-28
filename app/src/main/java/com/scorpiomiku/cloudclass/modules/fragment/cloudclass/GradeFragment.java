package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.Answer;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class GradeFragment extends BaseFragment {
    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.question_edit_text)
    TextView questionEditText;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.score_edit_view)
    EditText scoreEditView;
    @BindView(R.id.send_button)
    Button sendButton;
    Unbinder unbinder;
    private Answer answer;
    private ViewPager viewPager;
    private int position;
    private String score;

    public static GradeFragment newInstance(Answer homeAnswer, int position, ViewPager viewPager) {
        Bundle args = new Bundle();
        GradeFragment fragment = new GradeFragment();
        fragment.setArguments(args);
        fragment.setHomeAnswer(homeAnswer);
        fragment.setPosition(position);
        fragment.setViewPager(viewPager);
        return fragment;
    }

    public void setHomeAnswer(Answer homeAnswer) {
        this.answer = homeAnswer;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        addScore(score, "sign-" + answer.getId());
                        break;
                    case -1:
                        break;
                    case 1000:
                        MessageUtils.makeToast("打分成功");
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grade;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void initView() {
        Glide.with(getContext()).load(ConstantUtils.mediaHost + answer.getImage()).into(imageView);
        questionEditText.setText(answer.getContent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.back_button, R.id.send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                getActivity().onBackPressed();
                break;
            case R.id.send_button:
                score = scoreEditView.getText().toString().trim();
                HashMap<String, String> data = new HashMap<>();
                data.put("answerId", answer.getId());
                data.put("score", score);
                WebUtils.scoreAnswer(data, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JsonObject jsonObject = getJsonObj(response);
                        int result = jsonObject.get("result").getAsInt();
                        handler.sendEmptyMessage(result);
                    }
                });
                break;
        }
    }

    private void addScore(String score, String id) {
        HashMap<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("score", score);
        data.put("courseId", CloudClass.course.getCourse_id());
        data.put("userId", answer.getUper_id());
        WebUtils.addScore(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                if (jsonObject.get("result").getAsInt() == 1) {
                    handler.sendEmptyMessage(1000);
                }
            }
        });

    }
}
