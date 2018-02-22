package com.locensate.androidskillstack;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class NextActivity extends BaseActivity {

    @BindView(R.id.btn_next_activity)
    Button btnNextActivity;
    @BindView(R.id.btn_force_offline)
    Button btnForceOffline;
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
        return R.layout.activity_next;
    }

    @OnClick({R.id.btn_next_activity, R.id.btn_force_offline})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_activity:
                startActivity(new Intent(this, OtherActivity.class));
                break;
            case R.id.btn_force_offline:
                Intent intent = new Intent();
                intent.setAction("com.locensate.firstlinecode.broadcasttest.FORCE_OFFLINE");
                sendBroadcast(intent);
                break;
        }
    }
}
