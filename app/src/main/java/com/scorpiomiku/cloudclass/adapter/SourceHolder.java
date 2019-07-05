package com.scorpiomiku.cloudclass.adapter;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scorpiomiku.cloudclass.R;
import com.scorpiomiku.cloudclass.bean.MySource;
import com.scorpiomiku.cloudclass.utils.ConstantUtils;
import com.scorpiomiku.cloudclass.utils.DownloadUtils;
import com.scorpiomiku.cloudclass.utils.MessageUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ScorpioMiku on 2019/6/27.
 */

public class SourceHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.file_name)
    TextView fileName;
    @BindView(R.id.uper_name_text_view)
    TextView uperNameTextView;
    @BindView(R.id.load_progress_bar)
    ProgressBar loadProgressBar;
    @BindView(R.id.cover)
    LinearLayout cover;
    @BindView(R.id.whole_view)
    RelativeLayout wholeView;
    private View view;
    private MySource mySource;

    public SourceHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bindView(MySource mySource) {
        this.mySource = mySource;
        switch (mySource.getType()) {
            case 0:
                //ppt
                imageView.setImageResource(R.drawable.ppt);
                break;
            case 1:
                //doc
                imageView.setImageResource(R.drawable.word);
                break;
            case 2:
                //xls
                imageView.setImageResource(R.drawable.xls);
                break;
            case 3:
                //pdf
                imageView.setImageResource(R.drawable.pdf);
                break;
            case 4:
                //txt
                imageView.setImageResource(R.drawable.txt);
                break;
            default:
                imageView.setImageResource(R.drawable.other);
                break;
        }
        fileName.setText(mySource.getSource_name());
        uperNameTextView.setText(mySource.getUper_name());

    }

    @OnClick(R.id.whole_view)
    public void onViewClicked() {
        final ProgressDialog dialog = new ProgressDialog(itemView.getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setTitle("正在下载中");
        dialog.setMessage("请稍后...");
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show();

        DownloadUtils.get().download(itemView.getContext(), ConstantUtils.mediaHost + mySource.getSource_path(),
                "/download/", mySource.getSource_name(), new DownloadUtils.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess() {
                        wholeView.post(new Runnable() {
                            @Override
                            public void run() {
                                MessageUtils.makeToast("下载成功");
                                // 这里的弹框设置了进度条，下同
                                dialog.dismiss();

                                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                    return;
                                }

                                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/download/" + fileName);

                                try {
                                    openWPS(file.getPath());

                                } catch (Exception e) {

                                    e.printStackTrace();
                                }
                            }
                        });

                    }

                    @Override
                    public void onDownloading(int progress) {
                        dialog.setProgress(progress);
                    }

                    @Override
                    public void onDownloadFailed() {
                        wholeView.post(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });
                    }
                });
    }

    private void openWPS(String path) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(itemView.getContext(),
                    "com.scorpiomiku.cloudclass.fileprovider",
                    new File(path));
            intent.setData(uri);
            grantUriPermission(itemView.getContext(), uri, intent);
        } else {
            uri = Uri.fromFile(new File(path));
            intent.setData(uri);
        }
        intent.putExtras(bundle);
        try {
//            itemView.getContext().getApplicationContext().startActivity(Intent.createChooser(intent, "标题"));
            itemView.getContext().getApplicationContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void grantUriPermission(Context context, Uri fileUri, Intent intent) {
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }
}
