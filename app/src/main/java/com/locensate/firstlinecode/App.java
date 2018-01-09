package com.locensate.firstlinecode;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.litepal.LitePalApplication;

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
 * 时间： 2017/2/27 18:28
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */

public class App extends LitePalApplication {

    public static Application context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

//        InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
//        WXSDKEngine.initialize(this,config);

    }

    public static Context getApplication() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
