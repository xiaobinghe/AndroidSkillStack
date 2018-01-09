package com.locensate.firstlinecode;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.firstlinecode.utils.NotificationUtils;
import com.locensate.firstlinecode.utils.ToastUtil;
import com.locensate.firstlinecode.view.CustomTitleBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiMediaActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.btn_notification)
    Button btnNotification;
    @BindView(R.id.iv_add_photo)
    ImageView ivAddPhoto;
    @BindView(R.id.tv_multi_media_des)
    TextView tvMultiMediaDes;
    @BindView(R.id.activity_multi_media)
    LinearLayout activityMultiMedia;
    @BindView(R.id.btn_open_camera)
    Button btnOpenCamera;
    @BindView(R.id.btn_photo_album)
    Button btnPhotoAlbum;
    private Uri uriForFile;
    public static final int TAKE_PHOTO = 0x0010;
    private static final String TAG = "MultiMediaActivity";
    private BottomSheetDialog dialog;
    private static final int REQUEST_ALBUM = 0x0011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_media);
        ButterKnife.bind(this);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.hide();
        titleBar.setAddIsAble(false);
        titleBar.setTitle("多媒体测试");
    }

    private void sendNotification() {
        Intent intent = new Intent(App.getApplication(), MultiMediaActivity.class);
        intent.putExtra("test", "test");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Notice")
                .setContentInfo("You have a notification")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setLights(Color.GREEN, 1000, 1000)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        manager.notify(123456, notification);
    }

    protected void requestPermission() {
        // TODO Auto-generated method stub
        // 6.0以上系统才可以判断权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
            // 进入设置系统应用权限界面
            Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            startActivity(intent);
            return;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
            // 进入设置系统应用权限界面
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
            return;
        }
        return;
    }

    @OnClick({R.id.btn_notification, R.id.btn_open_camera, R.id.btn_photo_album, R.id.iv_add_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_notification:
                if (NotificationUtils.isNotificationEnabled(this)) {
                    sendNotification();
                } else {
                    ToastUtil.showToast("请允许接受通知");
                    requestPermission();
                }
                break;
            case R.id.btn_open_camera:
                break;
            case R.id.btn_photo_album:
                break;
            case R.id.iv_add_photo:
                dialog = new BottomSheetDialog(this);
                View inflate = LayoutInflater.from(this).inflate(R.layout.layout_bottom_take_photo, null);
                TextView takePhoto = (TextView) inflate.findViewById(R.id.tv_take_photo);
                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openCamera();
                    }
                });
                TextView album = (TextView) inflate.findViewById(R.id.tv_photo_album);
                album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openAlbum();
                    }
                });
                TextView cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(inflate);
                dialog.show();
                break;
        }
    }

    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(App.getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_ALBUM);
        }
    }

    private void openCamera() {
        File outputImage = new File(getExternalCacheDir(), "outputImage.jpg");
        try {
            if (outputImage.exists()) outputImage.delete();
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            uriForFile = FileProvider.getUriForFile(MultiMediaActivity.this, "com.locensate.firstlinecode.fileprovider", outputImage);
        } else {
            uriForFile = Uri.fromFile(outputImage);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: +==========" + requestCode, null);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Log.e(TAG, "onActivityResult: +==========" + resultCode, null);
                    try {
                        Log.e(TAG, "onActivityResult: +==========" + uriForFile, null);

                        InputStream is = getContentResolver().openInputStream(uriForFile);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        Log.e(TAG, "onActivityResult: +=========" + (bitmap == null), null);
                        if (dialog.isShowing()) dialog.dismiss();
                        ivAddPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_ALBUM:
                if (requestCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat();
                    }else{
                        handleImageBeforeKitKat();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat() {


    }

    private void handleImageOnKitKat() {


    }
}
