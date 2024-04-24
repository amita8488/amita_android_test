package com.example.myandroidtest.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidtest.R;

public class Content {

    public static final String BASE_URL = "https://acharyaprashant.org/api/v2/content/misc/";

    /* * @ Function Name      : showDialog
     * @ Function Params    : NONE
     * @ Function Purpose   : To Generate Common Dialog To Display Any Messgae
     * */
    public static Dialog errorDialog;

    public static Dialog showErrorOrOkDialog(Activity context, String message) {
        // code goes here
        if (errorDialog != null && errorDialog.isShowing()) {
            errorDialog.dismiss();
        }
        errorDialog = new Dialog(context, androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog);
        Window window = errorDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.requestFeature(window.FEATURE_NO_TITLE);
        errorDialog.setContentView(R.layout.dialog_error_body);
        errorDialog.setCancelable(false);

        TextView str_title = errorDialog.findViewById(R.id.str_title);
        str_title.setText(message);

        Button btn_okay = errorDialog.findViewById(R.id.btnOkay);
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss();
            }
        });

        return errorDialog;
    }

    public static void showFailed(Activity activity, Object errorString) {
        if (errorString instanceof String)
            Content.showErrorOrOkDialog(activity, (String) errorString).show();
        else
            Content.showErrorOrOkDialog(activity, activity.getResources().getString((int) errorString)).show();
    }

}
