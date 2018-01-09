package com.locensate.firstlinecode.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.firstlinecode.R;
import com.locensate.firstlinecode.utils.ToastUtil;

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
 * 时间： 2017/2/28 15:01
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */

public class CustomTitleBar extends LinearLayout implements View.OnClickListener {

    private final TextView title;
    private final ImageView add;
    private final ImageView back;
    public boolean enable = true;
    public ClickedButtonListener mListener;

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_title_bar, this);
        back = (ImageView) findViewById(R.id.iv_title_back);
        add = (ImageView) findViewById(R.id.iv_title_menu);
        title = (TextView) findViewById(R.id.tv_title_content);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }


    public void setTitle(String des) {
        title.setText(des);
    }

    public void setAddIsAble(boolean isAble) {
        if (isAble) {
            add.setVisibility(VISIBLE);
        } else {
            add.setVisibility(INVISIBLE);
        }
        add.setClickable(isAble);
        enable = isAble;
    }

    public boolean getAddIsAble() {
        return enable;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                ((Activity) getContext()).finish();
                break;
            case R.id.iv_title_menu:
                ToastUtil.showToast("You clicked carefully!");
                mListener.clickedMenu();
                break;
        }
    }

    public void clicked(ClickedButtonListener listener) {
        mListener = listener;
    }

    public interface ClickedButtonListener {
        void clickedMenu();
        void clickedBack();
    }
}
