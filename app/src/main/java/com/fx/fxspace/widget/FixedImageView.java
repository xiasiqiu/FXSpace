package com.fx.fxspace.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class FixedImageView  extends ImageView{
    private int mScreenHeight;

    public FixedImageView(Context paramContext) {
        this(paramContext,null);
    }
    public FixedImageView(Context paramContext, AttributeSet paramAttributeSet){
        this(paramContext,paramAttributeSet,0);
    }

    public FixedImageView(Context paramContext,AttributeSet paramAttributeSet,int paramInt){
        super(paramContext,paramAttributeSet,paramInt);
        init(paramContext,paramAttributeSet);
    }

    private void init(Context paramContext,AttributeSet paramAttributeSet){
            this.mScreenHeight=getScreenWidthHeight(paramContext)[1];
    }

    public static int[] getScreenWidthHeight(Context paramContext){
        int[]arrayOfInt=new int[2];
        if(paramContext==null)
            return arrayOfInt;
        DisplayMetrics localDisplayMetrics=new DisplayMetrics();
        ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int i=localDisplayMetrics.widthPixels;
        int j=localDisplayMetrics.heightPixels;
        arrayOfInt[0]=i;
        arrayOfInt[1]=j;
        return arrayOfInt;
    }
    protected void onMeasure(int paramInt1,int paramInt2){
        int i= View.MeasureSpec.getSize(paramInt1);
        View.MeasureSpec.getSize(paramInt1);
        setMeasuredDimension(i,this.mScreenHeight);
    }


}
