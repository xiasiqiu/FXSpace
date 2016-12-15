package com.fx.fxspace.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class AppUtil {

    /**
     * 获取app版本名或版本号
     */

    public static String getAppVersionName(Context context,String name){
        PackageManager pm=context.getPackageManager();
        PackageInfo info;
        try{
            info=pm.getPackageInfo(context.getPackageName(),0);
            if(name=="CODE"){
                return String.valueOf(info.versionCode);
            }else {
                return info.versionName;
            }

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取设备ID
     */

    public static String getDeviceId(Context context){
        TelephonyManager tm= (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static int dp2px(Context paramContext,float paramFloat){
        float scale=paramContext.getResources().getDisplayMetrics().density;
        return (int) (0.5F+paramFloat*scale);

    }
    public static int getWindowWidth(Context paramContext)
    {
        return getWindowManager(paramContext).getDefaultDisplay().getWidth();
    }
    public static WindowManager getWindowManager(Context paramContext)
    {
        return (WindowManager)paramContext.getSystemService(Context.WINDOW_SERVICE);
    }
}
