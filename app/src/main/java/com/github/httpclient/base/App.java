package com.github.httpclient.base;

import android.app.Application;
import android.content.Context;

/**
 * @data 2018-10-12
 * @desc
 */

public class App extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

}
