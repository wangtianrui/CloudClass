package com.scorpiomiku.cloudclass.modules.activity.cloudclass.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.scorpiomiku.cloudclass.CloudClass;
import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.base.BaseActivity;
import com.scorpiomiku.cloudclass.bean.HomeWork;
import com.scorpiomiku.cloudclass.utils.MessageUtils;
import com.scorpiomiku.cloudclass.utils.WebUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HandInHomeWorkActivity extends BaseActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.question_edit_text)
    EditText questionEditText;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.send_button)
    Button sendButton;
    private Uri mImageUri;
    private Boolean hasPic = false;
    private File imageFile;
    private final int REQUEST_TAKE_PHOTO_CODE = 1056;

    private HomeWork homeWork;

    @Override
    protected Handler initHandle() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        MessageUtils.makeToast("提交成功");
                        finish();
                        break;
                    case 0:
                        MessageUtils.makeToast("提交作业失败");
                        break;
                }
            }
        };
        return handler;
    }

    @Override
    public void iniview() {
        Intent intent = getIntent();
        homeWork = (HomeWork) intent.getSerializableExtra("homework");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hand_in_home_work;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_button, R.id.image_view, R.id.send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.image_view:
                takePhoto();
                break;
            case R.id.send_button:
                if (!hasPic) {
                    BitmapDrawable drawable =
                            (BitmapDrawable) getResources().getDrawable(R.drawable.home_work_default);
                    Bitmap bitmap = drawable.getBitmap();
                    String test = "test2.jpg";
                    String path = getFilesDir() + File.separator + test;
                    try {
                        OutputStream os = new FileOutputStream(path);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                        os.close();
                    } catch (Exception e) {
                        MessageUtils.loge(e);
                    }
                    imageFile = new File(path);
                }
                if (imageFile == null) {
                    MessageUtils.logd("提交作业的图片为空");
                } else {
                    String content = questionEditText.getText().toString().trim();
                    if (content.equals("")) {
                        MessageUtils.makeToast("提交文本内容不能为空");
                    } else {
                        WebUtils.addAnswer(
                                homeWork.getId(),
                                CloudClass.user.getPhone(),
                                content,
                                "test.jpg",
                                imageFile,
                                new Callback() {
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

                }
                break;
        }
    }


    private void takePhoto() {
        // 步骤一：创建存储照片的文件
        String path = getFilesDir() + File.separator + "images" + File.separator;
        File file = new File(path, "test.jpg");
        imageFile = file;
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //步骤二：Android 7.0及以上获取文件 Uri
            mImageUri = FileProvider.getUriForFile(HandInHomeWorkActivity.this,
                    "com.scorpiomiku.cloudclass.fileprovider", file);
        } else {
            //步骤三：获取文件Uri
            mImageUri = Uri.fromFile(file);
        }

        //步骤四：调取系统拍照
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO_CODE) {//获取系统照片上传

            Bitmap bm = null;
            try {
                bm = getBitmapFormUri(mImageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(bm);
            hasPic = true;
        }
    }

    public Bitmap getBitmapFormUri(Uri uri) throws FileNotFoundException, IOException {
        InputStream input = getContentResolver().openInputStream(uri);


        //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;//不加载到内存
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;

        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        input = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
            if (options <= 0)
                break;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


}
