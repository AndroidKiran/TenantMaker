package com.easy.make.tenantmaker.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.component.materialcomponent.MaterialProgressDialog;

/**
 * Created by ravi on 09/09/16.
 */
public class DialogUtils {

    public static void showMaterialProgressDialog(MaterialProgressDialog progressDialog, String title, String message, Context context) {

        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        int dividerId = progressDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = progressDialog.findViewById(dividerId);
        if (divider != null) {
            divider.setBackgroundColor(ActivityCompat.getColor(context, R.color.bg_grey));
        }
    }
}
