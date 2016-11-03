package com.javen.dialogstudy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Javen on 2016/11/2.
 */

public class DialogService extends Service {
    private Context mContext;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DialogManager.getInstance(mContext).addView();
            }
        },1000*2);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
