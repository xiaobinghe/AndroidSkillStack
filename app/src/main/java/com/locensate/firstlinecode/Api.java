package com.locensate.firstlinecode;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
 * 时间： 2017/6/13 10:20
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */
public class Api {


    private static final String BASE_URL = "";

    /**
     * 获取单例
     *
     * @return
     */
    public static final Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 创建单例
     */
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    /**
     * 私有构造方法
     */
    private Api() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .build();

        new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }
}
