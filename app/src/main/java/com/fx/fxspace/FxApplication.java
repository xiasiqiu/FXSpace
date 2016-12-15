package com.fx.fxspace;

import android.app.Application;
import android.content.Context;
import com.fx.fxspace.di.component.DaggerNetComponent;
import com.fx.fxspace.di.component.NetComponent;
import com.fx.fxspace.di.modules.NetModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Title:
 * Description:
 * Created by Administrator on 2016/12/7.
 * 作者：fx on 2016/12/7 20:16
 */

public class FxApplication extends Application {

    private static FxApplication instance;

    public static FxApplication get(Context context){
        return (FxApplication)context.getApplicationContext();
    }

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger();
        initNet();
        initDatabase();
        initTypeFace();
    }
    private void initTypeFace() {
        CalligraphyConfig calligraphyConfig =new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    private void initLogger(){
        LogLevel logLevel;
        if (!BuildConfig.API_ENV){
            logLevel = LogLevel.FULL;
        }else{
            logLevel = LogLevel.NONE;
        }
        Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .logLevel(logLevel) ;       // default LogLevel.FULL
    }
    private void initNet(){
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }
    private void initDatabase(){

    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public static FxApplication getInstance() {
        return instance;
    }

}
