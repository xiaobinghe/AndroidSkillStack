package com.locensate.androidskillstack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.locensate.androidskillstack.utils.ToastUtil;

public class NormalActivity extends AppCompatActivity {


    private static final String TAG = "NormalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        Log.e(TAG, "onCreate:+==============OnStart()执行了！", null);
        ToastUtil.showToast("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart:+==============OnStart()执行了！", null);
        ToastUtil.showToast("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume:+==============OnStart()执行了！", null);
        ToastUtil.showToast("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause:+==============OnStart()执行了！", null);
        ToastUtil.showToast("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop:+==============OnStart()执行了！", null);
        ToastUtil.showToast("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy:+==============OnStart()执行了！", null);
        ToastUtil.showToast("onDestroy");
    }
}
