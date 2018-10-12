package com.github.httpclient.base;

/**
 * @data 2018-10-12
 * @desc
 */

public class BaseBean<T> {

    public static final String STATUS = "status";
    public static final String MSG    = "msg";

    public T data;          // 数据
    public int code;      // 状态码
    public String msg;      // 请求结果

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
