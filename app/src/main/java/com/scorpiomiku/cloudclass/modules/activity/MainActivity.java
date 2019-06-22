package com.scorpiomiku.cloudclass.modules.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    private MenuItem classItem;
    private MenuItem scheduleItem;
    private MenuItem userItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        classItem = navigation.getMenu().findItem(R.id.navigation_class);
        scheduleItem = navigation.getMenu().findItem(R.id.navigation_schedule);
        userItem = navigation.getMenu().findItem(R.id.navigation_user);

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_class:
                        classItem.setIcon(R.drawable.ic_cloudclass_choosed);
                        scheduleItem.setIcon(R.drawable.ic_schedule_unchoosed);
                        userItem.setIcon(R.drawable.ic_user_unchoosed);
                        return true;
                    case R.id.navigation_schedule:
                        classItem.setIcon(R.drawable.ic_cloudclass_unchoosed);
                        scheduleItem.setIcon(R.drawable.ic_schedule_choosed);
                        userItem.setIcon(R.drawable.ic_user_unchoosed);
                        return true;
                    case R.id.navigation_user:
                        classItem.setIcon(R.drawable.ic_cloudclass_unchoosed);
                        scheduleItem.setIcon(R.drawable.ic_schedule_unchoosed);
                        userItem.setIcon(R.drawable.ic_user_choosed);
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
