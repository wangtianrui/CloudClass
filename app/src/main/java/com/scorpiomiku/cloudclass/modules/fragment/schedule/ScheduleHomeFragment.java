package com.scorpiomiku.cloudclass.modules.fragment.schedule;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.CourseModel;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class ScheduleHomeFragment extends BaseFragment {
    @BindView(R.id.weekNames)
    LinearLayout weekNames;
    @BindView(R.id.sections)
    LinearLayout sections;
    @BindView(R.id.weekPanel_1)
    LinearLayout weekPanel1;
    @BindView(R.id.weekPanel_2)
    LinearLayout weekPanel2;
    @BindView(R.id.weekPanel_3)
    LinearLayout weekPanel3;
    @BindView(R.id.weekPanel_4)
    LinearLayout weekPanel4;
    @BindView(R.id.weekPanel_5)
    LinearLayout weekPanel5;
    @BindView(R.id.weekPanel_6)
    LinearLayout weekPanel6;
    @BindView(R.id.weekPanel_7)
    LinearLayout weekPanel7;
    @BindView(R.id.mFreshLayout)
    MaterialRefreshLayout mFreshLayout;
    Unbinder unbinder;
    @BindView(R.id.course_table_add)
    FloatingActionButton courseTableAdd;

    private List<LinearLayout> mWeekViews = new ArrayList<>();
    private AlertDialog dialog;
    private String name;
    private String week;
    private String section;
    private String classroom;
    private int itemHeight;
    private int maxSection = 12;
    private AlertDialog.Builder builder;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        MessageUtils.makeToast("该时间段已有课程");
                        break;
                    case 1:
                        dialog.dismiss();
                        refreshData();
                        break;
                    case 1000:
                        initWeekCourseView();
                        break;
                    case 1002:
                        refreshData();
                        break;
                    case 1001:
                        refreshData();
                        break;
                    default:
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.schedule_home_fragment_layout;
    }

    @Override
    protected void refreshData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("userId", CloudClass.user.getPhone());
        WebUtils.getCourseTable(data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonObject jsonObject = getJsonObj(response);
                int result = jsonObject.get("result").getAsInt();
                if (result > 0) {
                    Gson gson = new Gson();
//                    MessageUtils.logd(jsonObject.get("values").toString()+"hahahaha");
                    CourseModel[] courseModels = gson.fromJson(jsonObject.get("values"), CourseModel[].class);
                    CourseTableMaker.loader(Arrays.asList(courseModels));
                    handler.sendEmptyMessage(1000);
                }
            }
        });
    }

    @Override
    protected void initView() {
        mWeekViews.add(weekPanel1);
        mWeekViews.add(weekPanel2);
        mWeekViews.add(weekPanel3);
        mWeekViews.add(weekPanel4);
        mWeekViews.add(weekPanel5);
        mWeekViews.add(weekPanel6);
        mWeekViews.add(weekPanel7);
        itemHeight = getResources().getDimensionPixelSize(R.dimen.sectionHeight);
        initWeekNameView();
        initSectionView();
//        initWeekCourseView();
        refreshData();
        setRefreshListener();
    }

    /**
     * 初始化课程表
     */
    private void initWeekCourseView() {
        for (int i = 0; i < mWeekViews.size(); i++) {
            initWeekPanel(mWeekViews.get(i), CourseTableMaker.courseModels[i]);
        }
    }

    /**
     * 下拉刷新
     */
    private void setRefreshListener() {
        mFreshLayout.setLoadMore(false);
        mFreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                clearChildView();
                initWeekCourseView();
                mFreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFreshLayout.finishRefreshing();
                    }
                }, 500);
            }

        });
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

    /**
     * 顶部周一到周日的布局
     **/
    private void initWeekNameView() {
        for (int i = 0; i < mWeekViews.size() + 1; i++) {
            TextView tvWeekName = new TextView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            if (i != 0) {
                lp.weight = 1;
                tvWeekName.setText("周" + intToZH(i));
                if (i == getWeekDay()) {
                    tvWeekName.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    tvWeekName.setTextColor(Color.parseColor("#4A4A4A"));
                }
            } else {
                lp.weight = 0.8f;
                tvWeekName.setText(getMonth() + "月");
            }
            tvWeekName.setGravity(Gravity.CENTER_HORIZONTAL);
            tvWeekName.setLayoutParams(lp);
            weekNames.addView(tvWeekName);
        }
    }

    /**
     * 左边节次布局，设定每天最多12节课
     */
    private void initSectionView() {
        for (int i = 1; i <= maxSection; i++) {
            TextView tvSection = new TextView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.sectionHeight));
            lp.gravity = Gravity.CENTER;
            tvSection.setGravity(Gravity.CENTER);
            tvSection.setText(String.valueOf(i));
            tvSection.setLayoutParams(lp);
            sections.addView(tvSection);
        }
    }

    /**
     * 当前星期
     */
    public int getWeekDay() {
        int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 当前月份
     */
    public int getMonth() {
        int w = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return w;
    }

    /**
     * 每次刷新前清除每个LinearLayout上的课程view
     */
    private void clearChildView() {
        for (int i = 0; i < mWeekViews.size(); i++) {
            if (mWeekViews.get(i) != null)
                if (mWeekViews.get(i).getChildCount() > 0)
                    mWeekViews.get(i).removeAllViews();
        }
    }


    public void initWeekPanel(LinearLayout ll, List<CourseModel> data) {

        if (ll == null || data == null || data.size() < 1)
            return;
        ll.removeAllViews();
        CourseModel firstCourse = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            final CourseModel courseModel = data.get(i);

            if (courseModel.getSection() == 0 || courseModel.getSpan() == 0)
                return;
            FrameLayout frameLayout = new FrameLayout(getActivity());

            CornerTextView tv = new CornerTextView(getActivity(),
                    ColorUtils.getCourseBgColor(courseModel.getCourseFlag()),
                    dip2px(getContext(), 3));
            LinearLayout.LayoutParams frameLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    itemHeight * courseModel.getSpan());
            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            if (i == 0) {
                frameLp.setMargins(0, (courseModel.getSection() - 1) * itemHeight, 0, 0);
            } else {
                frameLp.setMargins(0, (courseModel.getSection() - (firstCourse.getSection() + firstCourse.getSpan())) * itemHeight, 0, 0);
            }
            tv.setLayoutParams(tvLp);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(12);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setText(courseModel.getCourse_name() + "\n @" + courseModel.getClassroom_number());

            frameLayout.setLayoutParams(frameLp);
            frameLayout.addView(tv);
            frameLayout.setPadding(2, 2, 2, 2);

            ll.addView(frameLayout);
            firstCourse = courseModel;
            int index = i ;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder = new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher).setTitle("提示")
                            .setMessage("确定删除吗").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 你想做的事情
                                    CourseModel course = data.get(index);
                                    HashMap<String, String> data = new HashMap<>();
                                    data.put("id", course.getId());
                                    WebUtils.deleteCourseTable(data, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            JsonObject jsonObject = getJsonObj(response);
                                            int result = jsonObject.get("result").getAsInt();
                                            if (result == 1) {
                                                handler.sendEmptyMessage(1002);
                                            } else {
                                                handler.sendEmptyMessage(1001);
                                            }
                                        }
                                    });
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ToDo: 你想做的事情
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }
            });
        }
    }

    public static String intToZH(int i) {
        String[] zh = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] unit = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十"};

        String str = "";
        StringBuffer sb = new StringBuffer(String.valueOf(i));
        sb = sb.reverse();
        int r = 0;
        int l = 0;
        for (int j = 0; j < sb.length(); j++) {
            r = Integer.valueOf(sb.substring(j, j + 1));
            if (j != 0)
                l = Integer.valueOf(sb.substring(j - 1, j));
            if (j == 0) {
                if (r != 0 || sb.length() == 1)
                    str = zh[r];
                continue;
            }
            if (j == 1 || j == 2 || j == 3 || j == 5 || j == 6 || j == 7 || j == 9) {
                if (r != 0)
                    str = zh[r] + unit[j] + str;
                else if (l != 0)
                    str = zh[r] + str;
                continue;
            }
            if (j == 4 || j == 8) {
                str = unit[j] + str;
                if ((l != 0 && r == 0) || r != 0)
                    str = zh[r] + str;
                continue;
            }
        }
        if (str.equals("七"))
            str = "日";
        return str;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @OnClick(R.id.course_table_add)
    public void onViewClicked() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_course_table_dialog, null);

        dialog = new android.app.AlertDialog.Builder(getContext()).setView(view).create();
        final EditText etName = view.findViewById(R.id.et_name);
        final EditText etWeek = view.findViewById(R.id.et_week);
        final EditText etSection = view.findViewById(R.id.et_section);
        Button okButton = view.findViewById(R.id.ok_button);
        final EditText et_classroom = view.findViewById(R.id.et_classroom);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                } else {
                    return false; // 默认返回 false
                }
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                week = etWeek.getText().toString().trim();
                section = etSection.getText().toString().trim();
                classroom = et_classroom.getText().toString().trim();
                int span = 2;
                if (Integer.valueOf(section) == 9) {
                    span = 3;
                }
                HashMap<String, String> data = new HashMap<>();
                data.put("userId", CloudClass.user.getPhone());
                data.put("section", section);
                data.put("week", week);
                data.put("span", String.valueOf(span));
                data.put("courseName", name);
                data.put("classroomNumber", classroom);
                WebUtils.addCourseTable(data, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JsonObject jsonObject = getJsonObj(response);
                        handler.sendEmptyMessage(jsonObject.get("result").getAsInt());
                    }
                });

            }
        });
        dialog.show();
    }
}
