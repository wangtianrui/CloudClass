package com.scorpiomiku.cloudclass.modules.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseFragment;
import com.scorpiomiku.cloudclass.modules.fragment.cloudclass.CloudClassHomeFragment;
import com.scorpiomiku.cloudclass.modules.fragment.schedule.ScheduleHomeFragment;
import com.scorpiomiku.cloudclass.modules.fragment.user.UserHomeFragment;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    private MenuItem classItem;
    private MenuItem scheduleItem;
    private MenuItem userItem;
    private FragmentManager fragmentManager;

    private Handler handler;

    private String name;
    private String sex;
    private String academy;
    private String classNumber;
    private AlertDialog dialog;

    private BaseFragment[] fragments = {
            CloudClassHomeFragment.getInstance(),
            new ScheduleHomeFragment()
            , new UserHomeFragment()
    };
    private static final String TAG = "MainActivity";

    @SuppressLint("HandlerLeak")
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

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        break;
                    case 1:
                        CloudClass.user.setName(name);
                        CloudClass.user.setSex(sex);
                        CloudClass.user.setClass_number(classNumber);
                        CloudClass.user.setAcademy(academy);
                        dialog.dismiss();
                        MessageUtils.makeToast("更新成功");
                        ((UserHomeFragment) fragments[2]).setUI();
                        break;
                }
            }
        };
        if (CloudClass.user.getName().equals("")) {
            createAddInfor();
        }
        checkPermission();
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

    /**
     * 添加信息的dialog
     */
    private void createAddInfor() {
        View view = LayoutInflater.from(this).inflate(R.layout.add_information_dialog, null);

        dialog = new android.app.AlertDialog.Builder(this).setView(view).create();
        final EditText etName = view.findViewById(R.id.et_name);
        final EditText etClassnumber = view.findViewById(R.id.et_classnumber);
        final EditText etSex = view.findViewById(R.id.et_sex);
        Button okButton = view.findViewById(R.id.ok_button);
        final EditText etCollege = view.findViewById(R.id.et_college);
        dialog.setCanceledOnTouchOutside(false);
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
                sex = etSex.getText().toString().trim();
                classNumber = etClassnumber.getText().toString().trim();
                academy = etCollege.getText().toString().trim();
                HashMap<String, String> data = new HashMap<>();
                data.put("phone", CloudClass.user.getPhone());
                data.put("name", name);
                data.put("sex", sex);
                data.put("classNumber", classNumber);
                data.put("academy", academy);
                WebUtils.addInformation(data, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: " + e.getMessage());
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


    protected JsonObject getJsonObj(Response response) throws IOException {
        String result = response.body().string();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;

    }


    private void checkPermission() {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.SEND_SMS
                    },
                    1);
        }
    }
}
