package com.locensate.firstlinecode;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.locensate.firstlinecode.utils.ToastUtil;

public class DialogActivity extends AppCompatActivity {
    private static final String TAG = "DialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        ActionBar supportActionBar = getSupportActionBar();
        Log.e(TAG, "onCreate:+==============actionbar exist！" + (supportActionBar != null), null);

        if (supportActionBar != null) {
            supportActionBar.hide();
        }

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
