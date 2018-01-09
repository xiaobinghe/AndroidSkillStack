package com.locensate.firstlinecode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.locensate.firstlinecode.view.CustomTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataDurableActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.btn_file_save)
    Button btnFileSave;
    @BindView(R.id.btn_sp_save)
    Button btnSpSave;
    @BindView(R.id.btn_sql_save)
    Button btnSqlSave;
    @BindView(R.id.activity_data_durable)
    LinearLayout activityDataDurable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadurable);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        titleBar.setTitle("数据存储");
        titleBar.setAddIsAble(false);
    }

    @OnClick({R.id.btn_file_save, R.id.btn_sp_save, R.id.btn_sql_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_file_save:
                startActivity(new Intent(this, FileSaveTestActivity.class));
                break;
            case R.id.btn_sp_save:
                startActivity(new Intent(this,SpSaveTestActivity.class));
                break;
            case R.id.btn_sql_save:
                startActivity(new Intent(this,SQLiteSaveActivity.class));
                break;
        }
    }
}
