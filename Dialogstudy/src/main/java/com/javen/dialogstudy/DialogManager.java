package com.javen.dialogstudy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import static android.R.attr.width;

/**
 * Created by Javen on 2016/11/2.
 */

public class DialogManager {
    private static DialogManager instance;
    private Context mContext;
    private WindowManager wm;
    private DialogManager(Context context) {
        this.mContext = context;
        wm = (WindowManager)  context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static DialogManager getInstance(Context context){
        if (instance == null){
            synchronized (DialogManager.class){
                if (instance == null){
                    instance = new DialogManager(context);
                }
            }
        }
        return instance;
    }

    public WindowManager getWindowManager(){
        return  wm;
    }

    public void addView(){

        final ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.test);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wm.removeView(imageView);
            }
        });
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.BOTTOM;
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示window
        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// 焦点
        mParams.width = getWidth();
        mParams.height = getNavigationBarHeight();
        wm.addView(imageView,mParams);
    }

    /**
     * 获取顶部status bar 高度
     * @return
     */
    private int getStatusBarHeight() {
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
    /**
     * 获取底部 navigation bar 高度
     * @return
     */
    private int getNavigationBarHeight() {
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }
    private  int  getWidth(){
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width =outMetrics.widthPixels;
        Log.v("dbw", "screen width:" + width);
        return width;
    }

    private  int  getHeight(){
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int height = outMetrics.heightPixels;
        Log.v("dbw", "screen height:" + width);
        return height;
    }
}
