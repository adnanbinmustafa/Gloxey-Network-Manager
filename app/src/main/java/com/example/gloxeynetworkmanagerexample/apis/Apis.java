package com.example.gloxeynetworkmanagerexample.apis;

import android.content.Context;
import android.view.View;

import io.gloxey.gnm.interfaces.GloxeyCallback;
import io.gloxey.gnm.managers.ConnectionManager;

public class Apis {

    /***
     *  Get Weather Record
     *  Method : GET
     *
     * @param context
     * @param isDialog
     * @param view
     * @param volleyStringResponse
     */
    public static void getApiRecord(Context context,
                                        boolean isDialog,
                                        View view,
                                        GloxeyCallback.StringResponse volleyStringResponse) {


        String requestURL = "https://api.publicapis.org/entries";

        ConnectionManager.volleyStringRequest(context, isDialog, view, requestURL, "TestApi", volleyStringResponse);
    }

}
