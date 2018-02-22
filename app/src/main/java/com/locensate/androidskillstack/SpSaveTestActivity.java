package com.locensate.androidskillstack;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.androidskillstack.utils.ToastUtil;
import com.locensate.androidskillstack.view.CustomTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpSaveTestActivity extends AppCompatActivity {


    public static String SP_CONFIG = "sp_config";
    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.et_sp_key)
    EditText etSpKey;
    @BindView(R.id.et_sp_content)
    EditText etSpContent;
    @BindView(R.id.btn_sp_input)
    Button btnSpInput;
    @BindView(R.id.et_sp_output)
    EditText etSpOutput;
    @BindView(R.id.tv_sp_output)
    TextView tvSpOutput;
    @BindView(R.id.btn_sp_output)
    Button btnSpOutput;
    @BindView(R.id.tv_sp_des)
    TextView tvSpDes;
    @BindView(R.id.activity_sp_sava_test)
    LinearLayout activitySpSavaTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_save_test);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        titleBar.setTitle("Sp存储");
        titleBar.setAddIsAble(false);
        tvSpDes.setText("SharedPreferences是使用键值对形式存储数据的，目前支持6种数据类型的数据存储（int，String,float,boolean,long,StringSet）;" +
                "获取SharedPreference对象的方式有多种:\n 1、Context类中的getSharedPreference（String name,Mode mode）方法,其参数中name与文件名相关，Mode目前只有MODE_PRIVATE可用，" +
                "其他的Mode都被废弃了；\n 2、Activity类中的getPreferences（Mode mode）方法；\n 3、PreferenceManager类中的getDefaultSharedPreferences（Context context）方法,它默认以当前包名为前缀" +
                "来命名Sp文件。得到Sp对象后，就可以开始想Sp文件中存储数据了。\r\n（1）.调用Sp对象的edit（）方法；\r\n（2）.向Sp.Editor对象中添加数据，如edit.putString();\r\n(3).调用edit.apply()方法提交数据" +
                "从而完成数据存储操作；\r\n（4）.读取数据非常简单：获取到Sp对象，通过对应的数据类型调用get方法读取数据；\r\n 在");
    }

    @OnClick({R.id.btn_sp_input, R.id.btn_sp_output})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sp_input:
                saveSp();
                break;
            case R.id.btn_sp_output:
                outPutSp();
                break;
        }
    }

    private void outPutSp() {
        String out = getSharedPreferences(SP_CONFIG, MODE_PRIVATE).getString(etSpOutput.getText().toString(), "");
        if (!TextUtils.isEmpty(out)) {
            tvSpOutput.setText(out);
        } else {
            ToastUtil.showToast("无此数据");
            tvSpOutput.setText("");
        }
    }

    private void saveSp() {
        SharedPreferences sp = getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(etSpKey.getText().toString(), etSpContent.getText().toString());
        edit.apply();
    }
}
