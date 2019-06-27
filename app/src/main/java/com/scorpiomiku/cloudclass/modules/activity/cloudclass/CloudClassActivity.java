package com.scorpiomiku.cloudclass.modules.activity.cloudclass;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;

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
                    return true;
                case R.id.navigation_member:
                    mainItem.setIcon(R.drawable.ic_main_gray);
                    listItem.setIcon(R.drawable.ic_list_green);
                    seatItem.setIcon(R.drawable.ic_nav_seat_gray);
                    informItem.setIcon(R.drawable.ic_inform_gray);
                    return true;
                case R.id.navigation_seat:
                    mainItem.setIcon(R.drawable.ic_main_gray);
                    listItem.setIcon(R.drawable.ic_list_gray);
                    seatItem.setIcon(R.drawable.ic_nav_seat_green);
                    informItem.setIcon(R.drawable.ic_inform_gray);
                    return true;
                case R.id.navigation_inform:
                    mainItem.setIcon(R.drawable.ic_main_gray);
                    listItem.setIcon(R.drawable.ic_list_gray);
                    seatItem.setIcon(R.drawable.ic_nav_seat_gray);
                    informItem.setIcon(R.drawable.ic_inform_green);
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
    }

    @OnClick(R.id.back_button)
    public void onViewClicked() {
        finish();
    }

    private void changeFragment() {

    }
}
