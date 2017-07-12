package io.gloxey.apis;

import android.content.Context;
import android.view.View;

import io.gloxey.constants.Constants;
import io.gloxey.gnm.interfaces.VolleyResponse;
import io.gloxey.gnm.managers.ConnectionManager;

public class Apis {

    /***
     *  Get Weather Record
     *  Method : GET
     *
     * @param context
     * @param isDialog
     * @param view
     * @param city
     * @param volleyResponse
     */
    public static void getWeatherRecord(Context context,
                                        boolean isDialog,
                                        View view,
                                        String city,
                                        VolleyResponse volleyResponse) {

        String requestURL = Constants.ApiLink.SERVER_BASE_URL + city + Constants.ApiLink.APP_ID;

        ConnectionManager.volleyRequest(context, isDialog, view, requestURL, volleyResponse);
    }
}
