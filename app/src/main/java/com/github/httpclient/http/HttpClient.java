package com.github.httpclient.http;


import com.github.httpclient.constants.Api;
import com.github.httpclient.http.gson.CustomGsonConverterFactory;
import com.github.httpclient.http.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @data 2018-10-12
 * @desc
 */

public class HttpClient {

    private static HttpClient mInstance;
    private Retrofit mRetrofit;

    public static HttpClient getInstance(){
        if (mInstance == null) {
            synchronized (HttpClient.class){
                if (mInstance == null) {
                    mInstance = new HttpClient();
                }
            }
        }
        return mInstance;
    }

    private HttpClient(){
        configRetrofit();
    }

    private void configRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        // 设置错误重连
        builder.retryOnConnectionFailure(true);
        // 设置Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new LoggingInterceptor());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        //构建Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.SERVICE_URL)
                .client(builder.build())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> clz){
        return mRetrofit.create(clz);
    }

}
