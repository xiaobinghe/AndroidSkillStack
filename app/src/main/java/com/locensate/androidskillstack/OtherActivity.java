package com.locensate.androidskillstack;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class OtherActivity extends BaseActivity {

    @BindView(R.id.btn_force_offline_other)
    Button btnForceOfflineOther;
    @BindView(R.id.activity_next)
    LinearLayout activityNext;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    public int getLayout() {
        return R.layout.activity_other;
    }

    @OnClick(R.id.btn_force_offline_other)
    public void onClick() {
        Intent intent = new Intent();
        intent.setAction("com.locensate.firstlinecode.broadcasttest.FORCE_OFFLINE");
        sendBroadcast(intent);
    }
}
