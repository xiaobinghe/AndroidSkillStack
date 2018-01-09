package com.locensate.firstlinecode;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class RxDataBindingTestActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_content)
    TextView tvTitleContent;
    @BindView(R.id.tv_title_switch)
    TextView tvTitleSwitch;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btt_msg_code)
    Button bttMsgCode;
    @BindView(R.id.et_msg_code)
    EditText etMsgCode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.cb_remember_password)
    CheckBox cbRememberPassword;
    @BindView(R.id.tv_wenti)
    TextView tvWenti;
    @BindView(R.id.activity_rx_test)
    LinearLayout activityRxTest;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.et_set_psw)
    EditText etSetPsw;
    @BindView(R.id.tv_test)
    TextView mTvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);

        ActionBar actionBar = getActionBar();
        if (null != actionBar)
            actionBar.hide();
        ButterKnife.bind(this);
    }

    /**
     * @param view
     */
    @OnClick({R.id.tv_title_switch, R.id.btt_msg_code, R.id.btn_register, R.id.btn_login, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_switch:
                switchSurface();
                break;
            case R.id.btt_msg_code:
                break;
            case R.id.btn_register:
                break;
            case R.id.btn_login:
                Retrofit builder = new Retrofit.Builder()
                        .baseUrl("https://www.baidu.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

                ApiService service = builder.create(ApiService.class);
                service.test().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {

                            @Override
                            public void onError(Throwable e) {
                                mTvTest.setText(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                mTvTest.setText(s);
                            }
                        });

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void switchSurface() {
        boolean isRegister = tvTitleSwitch.getText().toString().equals("登录");
        tvTitleSwitch.setText(isRegister ? "注册" : "登录");
        tvTitleContent.setText(isRegister ? "登录" : "注册");
        llRegister.setVisibility(isRegister ? View.GONE : View.VISIBLE);
        llLogin.setVisibility(isRegister ? View.VISIBLE : View.GONE);
    }

}
