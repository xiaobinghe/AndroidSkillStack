package com.locensate.firstlinecode;

import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.locensate.firstlinecode.view.CustomTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class BroadCastActivity extends BaseActivity implements CustomTitleBar.ClickedButtonListener {


    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.actv_user_name)
    AutoCompleteTextView actvUserName;
    @BindView(R.id.actv_user_psw)
    AutoCompleteTextView actvUserPsw;
    @BindView(R.id.cb_remember_psw)
    AppCompatCheckBox cbRememberPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void initData() {}

    @Override
    protected void initView() {
        titleBar.setTitle("登录");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_broad_cast;
    }
    @Override
    public void clickedMenu() {

    }
    @Override
    public void clickedBack() {

    }
    @OnClick({R.id.btn_login, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
            case R.id.btn_next:
                startActivity(new Intent(this, NextActivity.class));
                finish();
                break;
        }
    }
}
