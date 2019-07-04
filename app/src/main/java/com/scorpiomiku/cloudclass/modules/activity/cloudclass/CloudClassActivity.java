package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.InformFragment;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.MainFragment;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.MemberListFragment;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.SeatFragment;
import com.scorpiomiku.cloudclass.power.PowerService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CloudClassActivity extends AppCompatActivity {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    private MenuItem mainItem;
    private MenuItem listItem;
    private MenuItem seatItem;
    private MenuItem informItem;
    private FragmentManager fragmentManager;
    private Fragment[] fragments = {
            new MainFragment(),
            new MemberListFragment(),
            new SeatFragment(),
            new InformFragment()
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    mainItem.setIcon(R.drawable.ic_main_green);
                    listItem.setIcon(R.drawable.ic_list_gray);
                    seatItem.setIcon(R.drawable.ic_nav_seat_gray);
                    informItem.setIcon(R.drawable.ic_inform_gray);
                    changeFragment(0);
                    return true;
                case R.id.navigation_member:
                    mainItem.setIcon(R.drawable.ic_main_gray);
                    listItem.setIcon(R.drawable.ic_list_green);
                    seatItem.setIcon(R.drawable.ic_nav_seat_gray);
                    informItem.setIcon(R.drawable.ic_inform_gray);
                    changeFragment(1);
                    return true;
                case R.id.navigation_seat:
                    mainItem.setIcon(R.drawable.ic_main_gray);
                    listItem.setIcon(R.drawable.ic_list_gray);
                    seatItem.setIcon(R.drawable.ic_nav_seat_green);
                    informItem.setIcon(R.drawable.ic_inform_gray);
                    changeFragment(2);
                    return true;
                case R.id.navigation_inform:
                    mainItem.setIcon(R.drawable.ic_main_gray);
                    listItem.setIcon(R.drawable.ic_list_gray);
                    seatItem.setIcon(R.drawable.ic_nav_seat_gray);
                    informItem.setIcon(R.drawable.ic_inform_green);
                    changeFragment(3);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_class);
        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        title.setText(CloudClass.course.getName());
        mainItem = navigation.getMenu().findItem(R.id.navigation_main);
        listItem = navigation.getMenu().findItem(R.id.navigation_member);
        seatItem = navigation.getMenu().findItem(R.id.navigation_seat);
        informItem = navigation.getMenu().findItem(R.id.navigation_inform);
        fragmentManager = getSupportFragmentManager();
        initFragmentManager();
        Intent intent = new Intent(this, PowerService.class);
        intent.putExtra("courseId", CloudClass.course.getCourse_id());
        intent.putExtra("userId", CloudClass.user.getPhone());
        if (CloudClass.user.getType() != 1) {
            startService(intent);
        }
    }

    @OnClick(R.id.back_button)
    public void onViewClicked() {
        finish();
    }

    /**
     * 初始化fragment管理器
     */
    private void initFragmentManager() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragments[0]);
        fragmentTransaction.add(R.id.container, fragments[1]);
        fragmentTransaction.add(R.id.container, fragments[2]);
        fragmentTransaction.add(R.id.container, fragments[3]);
        fragmentTransaction.hide(fragments[1]);
        fragmentTransaction.hide(fragments[2]);
        fragmentTransaction.hide(fragments[3]);
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
