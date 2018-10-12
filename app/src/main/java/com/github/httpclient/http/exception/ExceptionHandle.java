package com.github.httpclient.http.exception;

import com.github.httpclient.R;
import com.github.utils.UiUtils;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @data 2018-10-11
 * @desc 异常类型
 */

public class ExceptionHandle {

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof SocketTimeoutException){ //连接超时
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = UiUtils.getString(R.string.request_time_out_fail);
        } else if (e instanceof ConnectException) { //网络连接错误
            ex = new ResponseThrowable(e, ERROR.CONNECT_ERROR);
            ex.message = UiUtils.getString(R.string.request_connect_fail);
        } else if (e instanceof UnknownHostException) { //DNS解析错误
            ex = new ResponseThrowable(e, ERROR.UNKNOWN_HOST_ERROR);
            ex.message = UiUtils.getString(R.string.request_connect_fail);
        } else if (e instanceof IllegalStateException || e instanceof JsonSyntaxException) {//参数解析失败
            ex = new ResponseThrowable(e, ERROR.ILLEGAL_STATE_ERROR);
            ex.message = UiUtils.getString(R.string.request_illegal_state_fail);
        } else if (e instanceof RuntimeException) {//运行时异常
            ex = new ResponseThrowable(e, ERROR.RUNTIME_ERROR);
            ex.message = UiUtils.getString(R.string.request_server_busy_fail);
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.message = UiUtils.getString(R.string.request_ssl_fail);
        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = UiUtils.getString(R.string.request_the_unknown_fail);
        }
        return ex;
    }

    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 连接超时
         */
        static final int TIMEOUT_ERROR = 1000;
        /**
         * 网络连接错误
         */
        static final int CONNECT_ERROR = 1001;
        /**
         * DNS解析错误
         */
        static final int UNKNOWN_HOST_ERROR = 1002;
        /**
         * 参数解析失败
         */
        static final int ILLEGAL_STATE_ERROR = 1003;
        /**
         * 运行时错误
         */
        static final int RUNTIME_ERROR = 1004;
        /**
         * 未知错误
         */
        static final int UNKNOWN = 1004;
        /**
         * 证书出错
         */
        static final int SSL_ERROR = 1005;

    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }
}
