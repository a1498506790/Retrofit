package com.github.httpclient.http;

import com.github.httpclient.base.BaseBean;
import com.github.httpclient.http.exception.ExceptionHandle;
import com.github.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @data 2018-10-12
 * @desc
 */

public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {

    private static final String TAG = BaseObserver.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {}

    @Override
    public void onNext(BaseBean<T> tBaseBean) {
        int code = tBaseBean.getCode();
        if (code == 0) { //根据后台协议 code 状态值 默认 0 是成功
            onSuccess(tBaseBean.getData());
        }else{
            onFail(tBaseBean.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        String message = ExceptionHandle.handleException(e).message;
        LogUtils.e(TAG, message); //打印错误信息
        onFail(message);
    }

    @Override
    public void onComplete() {}

    public abstract void onSuccess(T t);

    public abstract void onFail(String msg);
}
