package com.github.httpclient.http.api;

import com.github.httpclient.base.BaseBean;
import com.github.httpclient.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @data 2018-10-12
 * @desc
 */

public interface ApiService {

    @GET("api/home/banners")
    Observable<BaseBean<BannerBean>> banners();

}
