package com.fx.fxspace.di.modules;

import com.fx.fxspace.presenter.WelcomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

@Module
public class WelcomeModule {
    private WelcomeContract.View view;
    public WelcomeModule(WelcomeContract.View view){
        this.view=view;
    }
    @Provides
    public WelcomeContract.View providerView(){
        return view;
    }
}
