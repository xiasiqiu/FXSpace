package com.fx.fxspace.view.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.fx.fxspace.FxApplication;
import com.fx.fxspace.R;
import com.fx.fxspace.di.component.DaggerWelcomeComponent;
import com.fx.fxspace.di.modules.WelcomeModule;
import com.fx.fxspace.presenter.WelcomeContract;
import com.fx.fxspace.presenter.WelcomePresenter;
import com.fx.fxspace.utils.AppUtil;
import com.fx.fxspace.utils.FileUtil;
import com.fx.fxspace.utils.PreferenceUtils;
import com.fx.fxspace.view.MainActivity;
import com.fx.fxspace.widget.FixedImageView;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View,EasyPermissions.PermissionCallbacks{
    @Bind(R.id.welcome_fiv_img)
    FixedImageView welcomeImg;
    @Inject
    WelcomePresenter presenter;

    private static final int PERMISSON_REQUESTCODE = 1;

    /**
     * 需要进行检查的权限
     */
    protected String[]needPermissions={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerWelcomeComponent.builder()
                .netComponent(FxApplication.get(this).getNetComponent())
                .welcomeModule(new WelcomeModule(this))
                .build().inject(this);
        initStatus();
    }

    private void initStatus(){
        if(Build.VERSION.SDK_INT>=21){
            View  decorView=getWindow().getDecorView();
            int option=View.SYSTEM_UI_FLAG_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        requestCodePermissions();
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,String[]permissions,int[]paramArrayOfInt){
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,paramArrayOfInt,this);

    }

    @AfterPermissionGranted(PERMISSON_REQUESTCODE)
    private void requestCodePermissions(){
        if(!EasyPermissions.hasPermissions(this,needPermissions)){
            EasyPermissions.requestPermissions(this,"需要这些权限",PERMISSON_REQUESTCODE,needPermissions);
        }else {
            setContentView(R.layout.activity_welcome);
            ButterKnife.bind(WelcomeActivity.this);
            delaySplash();
            String deviceId= AppUtil.getDeviceId(this);
            presenter.getWelcome(deviceId);
        }
    }

    private void delaySplash(){
        List<String>picList= FileUtil.getAllAD();
        if(picList.size()>0){
            Random random=new Random();
            int index=random.nextInt(picList.size());
            int imgIndex= PreferenceUtils.getPrefInt(this,"welcome_img_index",0);
            Logger.i("img_index="+imgIndex);
            if(index==imgIndex){
                if(index>=picList.size()){
                    index--;
                }else if(imgIndex==0){
                    if(index+1<picList.size()){
                        index++;
                    }
                }
            }
            PreferenceUtils.setPrefInt(this,"welcome_img_index",index);
            Logger.i("当前的picList.size="+picList.size()+"index="+index);

            try {
                File file=new File(picList.get(index));
                InputStream fis=new FileInputStream(file);
                welcomeImg.setImageDrawable(InputStream2Drawable(fis));
                animWelcomeImage();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public Drawable InputStream2Drawable(InputStream inputStream){
        Drawable drawable= BitmapDrawable.createFromStream(inputStream);
        return drawable;
    }


    public void animWelcomeImage(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(welcomeImg,"translationX",-100F);
        animator.setDuration(1500L).start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        showMissingPermissionDialog();
    }

    private void showMissingPermissionDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用需要这些权限;");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    public void startAppSettings(){
        Intent intent=new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    }
}
