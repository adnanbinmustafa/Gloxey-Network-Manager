package io.gloxey.gnm.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import io.gloxey.gnm.R;

public class CustomProgressDialog extends Dialog {


    public CustomProgressDialog(@NonNull Context context) {
        super(context);

//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.CENTER;
//        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.dlg_custom_progress, null);
        setContentView(view);
    }
}
