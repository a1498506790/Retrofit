package com.github.httpclient.http.gson;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.github.httpclient.R;
import com.github.httpclient.base.BaseBean;
import com.github.utils.UiUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @data 2018-10-12
 * @desc 自己实现 Retrofit 的 json 解析过程。主要是判断返回状态码是否是成功，成功后再去解析 Json，避免了返回数据类型不一致导致的解析异常问题。
 */

public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {

    private final Gson mGson;
    private final TypeAdapter<T> mAdapter;

    public CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.mAdapter = adapter;
    }
    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String jsonString = value.string();
        if(TextUtils.isEmpty(jsonString)) return null;
        try {
            JSONObject json = new JSONObject(jsonString);
            int status = json.getInt(BaseBean.STATUS);
            String msg = json.getString(BaseBean.MSG);
            if (0 == status) {// 请求成功，返回数据
                return mAdapter.fromJson(jsonString);
            } else {
                throw new RuntimeException(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(UiUtils.getString(R.string.request_fail));
        } finally {
            value.close();
        }
    }
}

