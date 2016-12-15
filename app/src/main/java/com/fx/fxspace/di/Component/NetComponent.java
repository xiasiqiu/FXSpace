package com.fx.fxspace.di.component;

import com.fx.fxspace.di.modules.NetModule;
import com.fx.fxspace.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Title:
 * Description:
 * Created by Administrator on 2016/12/7.
 * 作者：fx on 2016/12/7 20:24
 */
@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkHttp();
    Retrofit getReTrofit();
}
