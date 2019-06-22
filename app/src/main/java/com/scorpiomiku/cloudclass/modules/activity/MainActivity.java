package com.scorpiomiku.cloudclass.modules.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.CloudClassHomeFragment;
import com.scorpiomiku.cloudclass.modules.fragment.schedule.ScheduleHomeFragment;
import com.scorpiomiku.cloudclass.modules.fragment.user.UserHomeFragment;
import com.scorpiomiku.cloudclass.utils.MessageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity {


    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    private MenuItem classItem;
    private MenuItem scheduleItem;
    private MenuItem userItem;
    private FragmentManager fragmentManager;

    private BaseFragment[] fragments = {
            CloudClassHomeFragment.getInstance(),
            new ScheduleHomeFragment()
            , new UserHomeFragment()
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("");
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        classItem = navigation.getMenu().findItem(R.id.navigation_class);
        scheduleItem = navigation.getMenu().findItem(R.id.navigation_schedule);
        userItem = navigation.getMenu().findItem(R.id.navigation_user);
        fragmentManager = getSupportFragmentManager();
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_class:
                        classItem.setIcon(R.drawable.ic_cloudclass_choosed);
                        scheduleItem.setIcon(R.drawable.ic_schedule_unchoosed);
                        userItem.setIcon(R.drawable.ic_user_unchoosed);
                        changeFragment(0);
                        return true;
                    case R.id.navigation_schedule:
                        classItem.setIcon(R.drawable.ic_cloudclass_unchoosed);
                        scheduleItem.setIcon(R.drawable.ic_schedule_choosed);
                        userItem.setIcon(R.drawable.ic_user_unchoosed);
                        changeFragment(1);
                        return true;
                    case R.id.navigation_user:
                        classItem.setIcon(R.drawable.ic_cloudclass_unchoosed);
                        scheduleItem.setIcon(R.drawable.ic_schedule_unchoosed);
                        userItem.setIcon(R.drawable.ic_user_choosed);
                        changeFragment(2);
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragmentManager();
    }


    /**
     * 初始化fragment管理器
     */
    private void initFragmentManager() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragments[0]);
        fragmentTransaction.add(R.id.container, fragments[1]);
        fragmentTransaction.add(R.id.container, fragments[2]);
        fragmentTransaction.hide(fragments[1]);
        fragmentTransaction.hide(fragments[2]);
        fragmentTransaction.commit();
    }

    /**
     * 修改fragment
     *
     * @param index
     */
    private void changeFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == index) {
                fragmentTransaction.show(fragments[i]);
            } else {
                fragmentTransaction.hide(fragments[i]);
            }
        }
        fragmentTransaction.commit();
    }
}
