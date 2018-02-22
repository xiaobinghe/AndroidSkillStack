package com.locensate.androidskillstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LifeCyclerActivity extends AppCompatActivity {

    @BindView(R.id.btt_normal_activity)
    Button bttNormalActivity;
    @BindView(R.id.btt_dialog_activity)
    Button bttDialogActivity;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_content_des)
    TextView tvContentDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycler);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            CharSequence content = savedInstanceState.getCharSequence("content");
            etContent.setText(content);
        }
        tvContentDes.setText("1、Toast显示的时间的关系，我们通过Toast看不到完整的Activity的生命周期，但是我通过log看到了完整的Activity的生命周期，" +
                "完全符合下面图示中的生命周期规律。2、在Activity的生命周期方法中还有一个很重要的方法onSaveInstanceState(Bundle outState),此方法在activity销毁之前必然执行,可在此方法中保存临时数据，再在onCreate（）方法中取出数据。" )
        ;
    }

    @OnClick({R.id.btt_normal_activity, R.id.btt_dialog_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btt_normal_activity:
                etContent.setText("normal activity");
                startActivity(new Intent(this, NormalActivity.class));
                break;
            case R.id.btt_dialog_activity:
                etContent.setText("dialog activity");
                startActivity(new Intent(this, DialogActivity.class));
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("content", etContent.getText().toString());
    }
}
