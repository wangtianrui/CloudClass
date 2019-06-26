package com.scorpiomiku.cloudclass.modules.fragment.cloudclass;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.adapter.ClassAdapter;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.bean.Course;
import com.scorpiomiku.cloudclass.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class CloudClassHomeFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.fab)
    FloatingActionsMenu fab;
    private ClassAdapter adapter;
    private List<Course> mlist = new ArrayList<>();

    public static CloudClassHomeFragment getInstance() {
        return new CloudClassHomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cloudclass_home_fragment_layout;
    }

    @Override
    protected void refreshData() {
        mlist.clear();
        Course course = new Course();
        course.setName("Android");
        course.setInvite_code("1534");
        course.setTeacherNumber("于一");
        mlist.add(course);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        adapter = new ClassAdapter(getContext(), mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        initFloatButton();
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
     * 初始化悬浮按钮
     */
    private void initFloatButton() {
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.fab)
                .setPrimaryText("加入课堂按钮")
                .setSecondaryText("点击这里可以根据老师给予的邀请码加入课堂")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                            // User has pressed the prompt target
                        }
                    }
                })
                .show();

        FloatingActionButton actionButtonActivate = new FloatingActionButton(getContext());
        actionButtonActivate.setIcon(R.drawable.ic_join);
        actionButtonActivate.setTextDirection(FloatingActionButton.TEXT_DIRECTION_FIRST_STRONG_LTR);
        actionButtonActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageUtils.makeToast("加入");
            }
        });
        fab.addButton(actionButtonActivate);
    }
}
