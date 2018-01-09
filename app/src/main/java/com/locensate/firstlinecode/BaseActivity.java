package com.locensate.firstlinecode;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.locensate.firstlinecode.utils.ActivityManager;

import butterknife.ButterKnife;

import static com.locensate.firstlinecode.App.context;

/**
 * -------------------------------------
 * <p>
 * 项目名称： FirstLineCode
 * <p>
 * 版权：locensate.com 版权所有 2016
 * <p>
 * 公司主页：http://www.locensate.com/
 * <p>
 * 描述：
 * <p>
 * 作者： xiaobinghe
 * <p>
 * 时间： 2017/3/2 12:28
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static ForceOfflineBroadCastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(getLayout());
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        initView();
        initData();
    }

    protected abstract void initData();


    protected abstract void initView();


    public abstract int getLayout();

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new ForceOfflineBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.locensate.firstlinecode.broadcasttest.FORCE_OFFLINE");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    class ForceOfflineBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive( Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("You are forced to be offline,please try to login again!");
            builder.setTitle("Waring");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityManager.removeAll();
                    skip();
                }
            });
            builder.create().show();
        }
    }

    private void skip() {
        startActivity(new Intent(context, BroadCastActivity.class));
    }
}
