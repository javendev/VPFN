package com.javen.vpfn.common;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Javen on 16/8/25.
 */
public class DialogUtils {

    public static  void progressDialog2(Context context,String loadingText){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(loadingText);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public static  void progressDialog(Context context,String loadingText){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText(loadingText)
                .setConfirmText("Yes,delete it!")
                .show();
    }
}
