package com.javen.dialogstudy;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView appInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appInfos = (TextView) findViewById(R.id.appInfos);
    }

    /**
     * 6.0系统获取正在运行的app 只能获取到自身的
     */
    public void getAppInfos(View view){
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);

        StringBuffer sbf = new StringBuffer("正在运行的app：\n\n");
        for (ActivityManager.RunningAppProcessInfo runApp : runningAppProcesses) {
            sbf.append(runApp.processName+" "+runApp.importance+"\n");
        }
        sbf.append("\n\n");
        sbf.append("安装的包信息:\n\n");
        for (ApplicationInfo info:installedApplications) {
            sbf.append(packageManager.getApplicationLabel(info).toString()+" "+info.packageName+"\n");
        }
        sbf.append("\n\n");
        sbf.append("第三方包信息:\n\n");
        for (ApplicationInfo info:installedApplications) {
            if (filterApp(info)){
                sbf.append(packageManager.getApplicationLabel(info).toString()+" "+info.packageName+"\n");
            }
        }
        appInfos.setText(sbf.toString());

    }

    /**
     * 判断应用是不是第三方应用
     * @param info
     * @return
     */
    public boolean filterApp(ApplicationInfo info){
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) !=0){
            return true;
        }else  if((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
            return true;
        }
        return false;
    }

    public void T(View view){
        Toast.makeText(this, "T....", Toast.LENGTH_SHORT).show();
    }

    public void Dialog(View view){
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                addView();
//            }
//        },1000*5);

        startService(new Intent(this,DialogService.class));
    }

    private void addView(){
        WindowManager windowManager = DialogManager.getInstance(this).getWindowManager();

        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.ic_launcher);

        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.BOTTOM;
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示window
        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// 焦点
        mParams.width = windowManager.getDefaultDisplay().getWidth();
        mParams.height = 100;


        windowManager.addView(imageView,mParams);
    }
}
