package com.jirawat.jirawat_restaurant1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by nook on 2/11/2559.
 */
public class MyAlert {
    public void myDialog(Context context,
                         String strTitle,
                         String stringMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.danger);
        builder.setTitle(strTitle);
        builder.setMessage(stringMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }


        });
        builder.show();
    }

}