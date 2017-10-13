package io.gloxey.apis;

import android.content.Context;
import android.view.View;

import io.gloxey.constants.Constants;
import io.gloxey.gnm.interfaces.VolleyCallback;
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
    public static void getWeatherRecord(Context context,
                                        boolean isDialog,
                                        View view,
                                        VolleyCallback.StringResponse volleyStringResponse) {

//        http:api.themoviedb.org/3/api_key=b207f08a06fee161e46c3a3af4c0f6db

        String requestURL = Constants.ApiLink.SERVER_BASE_URL + Constants.ApiLink.TOP_RATED + "api_key=" + Constants.ApiLink.APP_ID;

        ConnectionManager.volleyStringRequest(context, isDialog, view, requestURL, "WeatherApi", volleyStringResponse);
    }

}
