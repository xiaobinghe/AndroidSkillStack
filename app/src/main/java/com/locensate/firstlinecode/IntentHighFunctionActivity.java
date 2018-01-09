package com.locensate.firstlinecode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * -------------------------------------
 * <p>
 * 项目名称： FirstLineCode
 * <p>
 * 版权：locensate.com 版权所有 2016
 * <p>
 * 公司主页：http://www.locensate.com/
 * <p>
 * 描述：使用intent可以调用系统的一些功能
 * <p>
 * 作者： xiaobinghe
 * <p>
 * 时间： 2017/2/27 19:13
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */
public class IntentHighFunctionActivity extends AppCompatActivity {

    @BindView(R.id.btt_open_chrome)
    Button bttOpenChrome;
    @BindView(R.id.btt_open_tel)
    Button bttOpenTel;
    @BindView(R.id.tv_other)
    TextView tvOther;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_function);
        ButterKnife.bind(this);
        tvOther.setText("Intent是连接Android四大组件的枢纽，可以分为显示Intent和隐式Intent，隐式Intent的用法主要用在调用系统的功能，如调用拨号界面，调用浏览器等等系统的功能；而显示Intent是我们经常会用到的，" +
                "可以启动四大组件，可以传递数据，亦可在Activity中回传数据，是Android程序中的重要角色！");
    }

    @OnClick({R.id.btt_open_chrome, R.id.btt_open_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btt_open_chrome:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.btt_open_tel:
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:18794223152"));
                startActivity(intent1);
                break;
        }
    }
}
