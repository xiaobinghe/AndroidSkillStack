package com.locensate.firstlinecode;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.locensate.firstlinecode.utils.ToastUtil;

import java.io.FileNotFoundException;

public class MainActivity extends Activity {

    //data
    private static final String TAG = "MainActivity";
    private static final int TAKE_BIG_PICTURE = 1;
    private static final int TAKE_SMALL_PICTURE = 2;
    private static final int CROP_BIG_PICTURE = 3;
    private static final int CROP_SMALL_PICTURE = 4;
    private static final int CHOOSE_BIG_PICTURE = 5;
    private static final int CHOOSE_SMALL_PICTURE = 6;
    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";
    private static final int CROP_CHOOSE_BIG = 7;
    private Uri imageUri;//to store the big bitmap

    //views
    private ImageView imageView;

    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 2);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * Handle click events
     */
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.buttonTakeBigPicture:
                if (imageUri == null)
                    Log.e(TAG, "image uri can't be null");
                //capture a big bitmap and store it in Uri
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_BIG_PICTURE);
                break;
            case R.id.buttonTakeSmallPicture:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_SMALL_PICTURE);
                break;
            case R.id.buttonChooseBigPicture:
                intent = new Intent(Intent.ACTION_GET_CONTENT, null);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 2);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 600);
                intent.putExtra("outputY", 300);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", false);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("noFaceDetection", false); // no face detection
                startActivityForResult(intent, CHOOSE_BIG_PICTURE);
                break;
            case R.id.buttonChooseSmallPicture:
                intent = new Intent(Intent.ACTION_GET_CONTENT, null);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 2);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 100);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("noFaceDetection", true); // no face detection
                startActivityForResult(intent, CHOOSE_SMALL_PICTURE);
                break;
            default:
                break;
        }
    }

    /**
     * Activity life cycle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.locensate.firstlinecode.R.layout.activity_main);
        if (getActionBar() != null) getActionBar().hide();
        //views
        imageView = (ImageView) findViewById(R.id.imageView);

        //instantiate
        imageUri = Uri.parse(IMAGE_FILE_LOCATION);
    }


    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (null != imagePath) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            ToastUtil.showToast("图片获取失败");
        }
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        Cursor query = getContentResolver().query(externalContentUri, null, selection, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                path = query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            query.close();
        }
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {//result is not correct
            Log.e(TAG, "requestCode = " + requestCode);
            Log.e(TAG, "resultCode = " + resultCode);
            Log.e(TAG, "data = " + data);
            return;
        } else {
            switch (requestCode) {
                case TAKE_BIG_PICTURE:
                    Log.d(TAG, "TAKE_BIG_PICTURE: data = " + data);//it seems to be null
                    //TODO sent to crop
                    cropImageUri(imageUri, 800, 800, CROP_BIG_PICTURE);
                    //or decode as bitmap and display it
                    // if(imageUri != null){
                    // Bitmap bitmap = decodeUriAsBitmap(imageUri);
                    // imageView.setImageBitmap(bitmap);
                    // }
                    break;
                case CROP_BIG_PICTURE://from crop_big_picture
                    Log.d(TAG, "CROP_BIG_PICTURE: data = " + data);//it seems to be null

                    if (imageUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(imageUri);
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
                case TAKE_SMALL_PICTURE:
                    Log.i(TAG, "TAKE_SMALL_PICTURE: data = " + data);
                    //TODO sent to crop
                    cropImageUri(imageUri, 480, 480, CROP_SMALL_PICTURE);

                case CROP_SMALL_PICTURE:

//                    if (requestCode == RESULT_OK) {
//                        if (Build.VERSION.SDK_INT >= 19) {
//                            handleImageOnKitKat(data);
//                        } else {
//                            handleImageBeforeKitKat(data);
//                        }
//                    }

                    if (imageUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(imageUri);
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Log.e(TAG, "CROP_SMALL_PICTURE: data = " + data);
                    }
                    break;
                case CHOOSE_BIG_PICTURE:
                    Log.d(TAG, "CHOOSE_BIG_PICTURE: data = " + data);//it seems to be null
//                    cropImageUri(imageUri,800,800,CROP_CHOOSE_BIG);
                    if (imageUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(imageUri);
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
                case CROP_CHOOSE_BIG:
                    Log.d(TAG, "CHOOSE_BIG_PICTURE: data = " + data);//it seems to be null

                    if (imageUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(data.getData());
                        imageView.setImageBitmap(bitmap);
                    }
                    break;
                case CHOOSE_SMALL_PICTURE:
                    if (imageUri != null) {
                        Bitmap bitmap = data.getParcelableExtra("data");
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Log.e(TAG, "CHOOSE_SMALL_PICTURE: data = " + data);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
}
