package com.locensate.androidskillstack.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.locensate.androidskillstack.App;
import com.locensate.androidskillstack.R;

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
 * 时间： 2017/7/19 17:52
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */

public class CustomView extends View {

    private Paint mCirclePaint;
    private float[] lines = {160, 350, 240, 350, 200, 365, 200, 435, 150, 450, 250, 450, 400, 350, 350, 450, 400, 400, 450, 450};

    public CustomView(Context context) {
        super(context);
        init();
    }


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void init() {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.STROKE);//设置绘制模式
        mCirclePaint.setStrokeWidth(10);//设置线条宽度
        mCirclePaint.setStrokeCap(Paint.Cap.SQUARE);//线帽的样式
        mCirclePaint.setAlpha(125);
        mCirclePaint.setAntiAlias(true);//设置抗锯齿
        mCirclePaint.setDither(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(255, 225, 200, 1000);
        canvas.drawCircle(150, 150, 100, mCirclePaint);
        canvas.drawPoint(470, 150, mCirclePaint);
        canvas.drawRect(320, 120, 380, 180, mCirclePaint);
        canvas.drawLine(0, 300, 1080, 300, mCirclePaint);
        canvas.drawBitmap(BitmapFactory.decodeResource(App.getApplication().getResources(), R.mipmap.ic_launcher, null), 550, 80, mCirclePaint);
        canvas.drawRoundRect(750, 80, 950, 200, 20, 10, mCirclePaint);
        canvas.drawLines(lines, mCirclePaint);

//        canvas.drawArc();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            canvas.drawOval(150f,450f,550f,350f,null);
        }
    }
}
