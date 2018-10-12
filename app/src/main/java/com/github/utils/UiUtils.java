package com.github.utils;

import android.content.Context;
import android.content.res.Resources;

import com.github.httpclient.base.App;

/**
 * @data 2018-10-12
 * @desc
 */

public class UiUtils {

    /**
     * 获取上下文
     */
    public static Context getContext() {
        return App.getContext();
    }

    /**
     * 获取资源操作类
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取字符串资源
     *
     * @param id 资源id
     * @return 字符串
     */
    public static String getString(int id) {
        return getResources().getString(id);
    }

}
