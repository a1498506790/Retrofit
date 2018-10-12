package com.github.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.httpclient.base.BaseBean;
import com.github.httpclient.bean.BannerBean;
import com.github.httpclient.http.BaseObserver;
import com.github.httpclient.http.HttpClient;
import com.github.httpclient.http.RxTransformer;
import com.github.httpclient.http.api.ApiService;
import com.github.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpClient.getInstance().create(ApiService.class)
                        .banners()
                        .compose(RxTransformer.<BaseBean<BannerBean>>setThread())
                        .subscribe(new BaseObserver<BannerBean>() {
                            @Override
                            public void onSuccess(BannerBean bannerBean) {
                                LogUtils.e("test", "请求成功");
                            }

                            @Override
                            public void onFail(String msg) {
                                LogUtils.e("test", "请求失败");
                            }
                        });
            }
        });
    }
}
