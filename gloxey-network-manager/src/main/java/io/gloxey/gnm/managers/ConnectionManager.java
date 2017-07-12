package io.gloxey.gnm.managers;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import io.gloxey.gnm.app.AppController;
import io.gloxey.gnm.interfaces.RetroResponse;
import io.gloxey.gnm.interfaces.VolleyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionManager {

    /**
     * Generic Retrofit request
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param networkCall
     * @param retroResponse
     * @param <T>
     */
    public static <T> void retroRequest(final Context _context, boolean _isDialog, final View _view, Call<T> networkCall, final RetroResponse retroResponse) {

        if (isNetwork(_context)) {
            retroResponse.isNetwork(true);

            if (_isDialog) {
                showLoader(_context, _view);
            }

            networkCall.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    hideLoader(_view);
                    retroResponse.onResponse(call, response);
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    hideLoader(_view);
                    retroResponse.onFailure(call, t);
                }
            });
        } else {
            hideLoader(_view);
            retroResponse.isNetwork(false);
        }
    }

    /**
     * Volley Post/put/delete request without header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestMethod
     * @param _params
     * @param _volleyResponse
     */
    public static void volleyRequest(final Context _context,
                                     boolean _isDialog,
                                     final View _view,
                                     String _url,
                                     int _requestMethod,
                                     final HashMap<String, String> _params,
                                     final VolleyResponse _volleyResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyResponse.isNetwork(true);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            StringRequest serverRequest = new StringRequest(_requestMethod, _url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyResponse.onResponse(response);
                }

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set error in callback
                     */
                    _volleyResponse.onErrorResponse(error);

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return _params;
                }
            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest);
        } else {
            /**
             * Set network false in callback
             */
            _volleyResponse.isNetwork(false);
        }
    }


    /**
     * Volley Post/put/delete request with header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestMethod
     * @param _params
     * @param _headers
     * @param _volleyResponse
     */
    public static void volleyRequest(final Context _context,
                                     boolean _isDialog,
                                     final View _view,
                                     String _url,
                                     int _requestMethod,
                                     final HashMap<String, String> _params,
                                     final HashMap<String, String> _headers,
                                     final VolleyResponse _volleyResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyResponse.isNetwork(true);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            StringRequest serverRequest = new StringRequest(_requestMethod, _url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyResponse.onResponse(response);
                }

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set error in callback
                     */
                    _volleyResponse.onErrorResponse(error);

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }

                @Override
                protected Map<String, String> getParams() {
                    return _params;
                }
            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest);
        } else {
            /**
             * Set network false in callback
             */
            _volleyResponse.isNetwork(false);

        }
    }

    /**
     * Volley Get request without header and params
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _volleyResponse
     */
    public static void volleyRequest(final Context _context,
                                     boolean _isDialog,
                                     final View _view,
                                     String _url,
                                     final VolleyResponse _volleyResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyResponse.isNetwork(true);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            StringRequest serverRequest = new StringRequest(Request.Method.GET, _url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyResponse.onResponse(response);
                }

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set error in callback
                     */
                    _volleyResponse.onErrorResponse(error);

                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest);

        } else {

            /**
             * Set network false in callback
             */
            _volleyResponse.isNetwork(false);

        }
    }

    /**
     * Volley JsonObjectRequest
     * Method POST / PUT / DELETE  with header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _jsonParamObject
     * @param _requestMethod
     * @param _headers
     * @param _volleyResponse
     */

    public static void volleyRequestJSON(final Context _context,
                                         final boolean _isDialog,
                                         final View _view,
                                         String _url,
                                         JSONObject _jsonParamObject,
                                         int _requestMethod,
                                         final HashMap<String, String> _headers,
                                         final VolleyResponse _volleyResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyResponse.isNetwork(true);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */

            JsonObjectRequest serverRequest = new JsonObjectRequest(_requestMethod, _url, _jsonParamObject, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // I have converted this to String, becasue i don't want to change my other stuff :D
                    //again i'll convert back into json object for normal flow ;)
                    // Bear with me :D :p

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyResponse.onResponse(response.toString());
                }

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set error in callback
                     */
                    _volleyResponse.onErrorResponse(error);

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }


            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest);
        } else {
            /**
             * Set network false in callback
             */
            _volleyResponse.isNetwork(false);
        }
    }


    /**
     * Volley JSON object request
     * Method Post / PUT / DELETE without header
     *
     * @param _context
     * @param _view
     * @param _isDialog
     * @param _url
     * @param _jsonParamObject
     * @param _requestMethod
     * @param _volleyResponse
     */
    public static void volleyRequestJSON(final Context _context,
                                         final View _view,
                                         boolean _isDialog,
                                         String _url,
                                         JSONObject _jsonParamObject,
                                         int _requestMethod,
                                         final VolleyResponse _volleyResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyResponse.isNetwork(true);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */

            JsonObjectRequest serverRequest = new JsonObjectRequest(_requestMethod, _url, _jsonParamObject, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // I have converted this to String, becasue i don't want to change my other stuff :D
                    // I am converting JSONObject into string and then again i'll convert back into json object for normal flow ;)
                    // Bear with me :D :p

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyResponse.onResponse(response.toString());

                }

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set error in callback
                     */
                    _volleyResponse.onErrorResponse(error);

                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest);
        } else {
            /**
             * Set network false in callback
             */
            _volleyResponse.isNetwork(false);
        }
    }


    /**
     * VOlley JsonObjectRequest
     * Method GET
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _volleyResponse
     */
    public static void volleyRequestJSON(final Context _context,
                                         boolean _isDialog,
                                         final View _view,
                                         String _url,
                                         final VolleyResponse _volleyResponse) {
        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyResponse.isNetwork(true);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            JsonObjectRequest serverRequest = new JsonObjectRequest(Request.Method.GET, _url, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    // I have converted this to String, becasue i don't want to change my other stuff :D
                    // I am converting JSONObject into string and then again i'll convert back into json object for normal flow ;)
                    // Bear with me :D :p

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyResponse.onResponse(response.toString());
                }

            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set error in callback
                     */
                    _volleyResponse.onErrorResponse(error);

                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest);
        } else {
            /**
             * Set network false in callback
             */
            _volleyResponse.isNetwork(false);
        }
    }


    /**
     * Volley Error Handler Helper
     *
     * @param context
     * @param error
     */
//    private void volleyErrorHandler(Context context, VolleyError error) {
//        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//        } else if (error instanceof AuthFailureError) {
//        } else if (error instanceof ServerError) {
//        } else if (error instanceof NetworkError) {
//        } else if (error instanceof ParseError) {
//        }
//    }

    /**
     * Check network connectivity
     *
     * @param context
     * @return
     */
    public static boolean isNetwork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private static ACProgressFlower dialog = null;

    /**
     * Show progress
     *
     * @param _context
     */
    private static void showLoader(Context _context, View _view) {

        if (_view == null) {
            dialog = new ACProgressFlower.Builder(_context)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .fadeColor(Color.DKGRAY).build();
            dialog.show();
        } else {
            _view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Dismiss Dialog Progress
     */

    private static void hideLoader(View _view) {

        if (_view == null) {
            if (dialog != null) {
                dialog.dismiss();
            }
        } else {
            _view.setVisibility(View.GONE);
        }

    }


}

