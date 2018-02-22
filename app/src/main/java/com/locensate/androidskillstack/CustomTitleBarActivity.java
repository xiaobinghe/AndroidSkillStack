package com.locensate.androidskillstack;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.androidskillstack.view.CustomTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomTitleBarActivity extends AppCompatActivity {

    @BindView(R.id.ctb_test_activity)
    CustomTitleBar ctbTestActivity;
    @BindView(R.id.tv_custom_des)
    TextView tvCustomDes;
    @BindView(R.id.activity_custom_title_bar)
    LinearLayout activityCustomTitleBar;
    @BindView(R.id.btt_switch_title_bar)
    Button bttSwitchTitleBar;
    @BindView(R.id.btt_set_title)
    Button bttSetTitle;
    @BindView(R.id.et_reset_title)
    EditText etResetTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_title_bar);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        initData();
    }

    private void initData() {
        bttSwitchTitleBar.setText("隐藏添加按钮");
        bttSetTitle.setText("重置标题");
        tvCustomDes.setText("自定义控件需要通过LayoutInflater.from().inflate()完成初始化布局，然后初始化控件，完成相应的逻辑编写，不再赘述。特别注意的是构造方法的使用。。。");
    }

    @OnClick({R.id.btt_set_title, R.id.btt_switch_title_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btt_set_title:
                ctbTestActivity.setTitle(etResetTitle.getText().toString());
                break;
            case R.id.btt_switch_title_bar:
                if (ctbTestActivity.getAddIsAble()) {
                    bttSwitchTitleBar.setText("显示添加按钮");
                    ctbTestActivity.setAddIsAble(false);
                } else {
                    bttSwitchTitleBar.setText("隐藏添加按钮");
                    ctbTestActivity.setAddIsAble(true);
                }
                break;
        }
    }
}
