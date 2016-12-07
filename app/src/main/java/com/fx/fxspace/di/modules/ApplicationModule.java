package com.fx.fxspace.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Title:
 * Description:
 * Created by Administrator on 2016/12/7.
 * 作者：fx on 2016/12/7 20:17
 */

@Module
public class ApplicationModule {
    private Context context;
    public ApplicationModule(Context context){
        this.context=context;
    }
    @Provides
    @Singleton
    public Context providerContext(){
        return context;
    }
}
