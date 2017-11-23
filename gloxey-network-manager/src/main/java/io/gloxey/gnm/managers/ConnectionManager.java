package io.gloxey.gnm.managers;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import io.gloxey.gnm.app.AppController;
import io.gloxey.gnm.interfaces.GloxeyCallback;

public class ConnectionManager {


    /**
     *==============================================================================================
     * Volley String Request
     *==============================================================================================
     */

    /**
     * Volley Post/put/delete request without header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestMethod
     * @param _params
     * @param _requestTag
     * @param _volleyStringResponse
     */
    public static void volleyStringRequest(final Context _context,
                                           boolean _isDialog,
                                           final View _view,
                                           String _url,
                                           int _requestMethod,
                                           final HashMap<String, String> _params,
                                           final String _requestTag,
                                           final GloxeyCallback.StringResponse _volleyStringResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyStringResponse.isConnected(true, _requestTag);

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
                    _volleyStringResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyStringResponse, _requestTag);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return _params;
                }
            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyStringResponse.isConnected(false, _requestTag);
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
     * @param _requestTag
     * @param _volleyStringResponse
     */
    public static void volleyStringRequest(final Context _context,
                                           boolean _isDialog,
                                           final View _view,
                                           String _url,
                                           int _requestMethod,
                                           final HashMap<String, String> _params,
                                           final HashMap<String, String> _headers,
                                           final String _requestTag,
                                           final GloxeyCallback.StringResponse _volleyStringResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyStringResponse.isConnected(true, _requestTag);

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
                    _volleyStringResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyStringResponse, _requestTag);
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
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyStringResponse.isConnected(false, _requestTag);

        }
    }

    /**
     * Volley Get request without header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestTag
     * @param _volleyStringResponse
     */
    public static void volleyStringRequest(final Context _context,
                                           boolean _isDialog,
                                           final View _view,
                                           String _url,
                                           final String _requestTag,
                                           final GloxeyCallback.StringResponse _volleyStringResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyStringResponse.isConnected(true, _requestTag);

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
                    _volleyStringResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyStringResponse, _requestTag);
                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);

        } else {

            /**
             * Set network false in callback
             */
            _volleyStringResponse.isConnected(false, _requestTag);

        }
    }

    /**
     * Volley Get request with header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _headers
     * @param _requestTag
     * @param _volleyStringResponse
     */
    public static void volleyStringRequest(final Context _context,
                                           boolean _isDialog,
                                           final View _view,
                                           String _url,
                                           final HashMap<String, String> _headers,
                                           final String _requestTag,
                                           final GloxeyCallback.StringResponse _volleyStringResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyStringResponse.isConnected(true, _requestTag);

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
                    _volleyStringResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyStringResponse, _requestTag);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }

            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyStringResponse.isConnected(false, _requestTag);

        }
    }


    /**
     *==============================================================================================
     * Volley JSON Object Request
     *==============================================================================================
     */


    /**
     * Volley JsonObjectRequest
     * Method POST / PUT / DELETE  with header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestMethod
     * @param _jsonParamObject
     * @param _headers
     * @param _requestTag
     * @param _volleyJSONResponse
     */
    public static void volleyJSONRequest(final Context _context,
                                         final boolean _isDialog,
                                         final View _view,
                                         String _url,
                                         int _requestMethod,
                                         JSONObject _jsonParamObject,
                                         final HashMap<String, String> _headers,
                                         final String _requestTag,
                                         final GloxeyCallback.JSONResponse _volleyJSONResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyJSONResponse.isConnected(true, _requestTag);

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

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyJSONResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyJSONResponse, _requestTag);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }


            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyJSONResponse.isConnected(false, _requestTag);
        }
    }


    /**
     * Volley JSON object request
     * Method Post / PUT / DELETE without header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestMethod
     * @param _jsonParamObject
     * @param _requestTag
     * @param _volleyJSONResponse
     */
    public static void volleyJSONRequest(final Context _context,
                                         boolean _isDialog,
                                         final View _view,
                                         String _url,
                                         int _requestMethod,
                                         JSONObject _jsonParamObject,
                                         final String _requestTag,
                                         final GloxeyCallback.JSONResponse _volleyJSONResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyJSONResponse.isConnected(true, _requestTag);

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
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyJSONResponse.onResponse(response, _requestTag);

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
                    volleyErrorHandler(error, _volleyJSONResponse, _requestTag);
                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyJSONResponse.isConnected(false, _requestTag);
        }
    }


    /**
     * Volley JSON object request without header
     * Method GET
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestTag
     * @param _volleyJSONResponse
     */
    public static void volleyJSONRequest(final Context _context,
                                         boolean _isDialog,
                                         final View _view,
                                         String _url,
                                         final String _requestTag,
                                         final GloxeyCallback.JSONResponse _volleyJSONResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyJSONResponse.isConnected(true, _requestTag);

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
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyJSONResponse.onResponse(response, _requestTag);

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
                    volleyErrorHandler(error, _volleyJSONResponse, _requestTag);
                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyJSONResponse.isConnected(false, _requestTag);
        }
    }


    /**
     * Volley JSON object request with header
     * Method GET
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _headers
     * @param _requestTag
     * @param _volleyJSONResponse
     */
    public static void volleyJSONRequest(final Context _context,
                                         final boolean _isDialog,
                                         final View _view,
                                         String _url,
                                         final HashMap<String, String> _headers,
                                         final String _requestTag,
                                         final GloxeyCallback.JSONResponse _volleyJSONResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyJSONResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */

            JsonObjectRequest serverRequest = new JsonObjectRequest(_url, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyJSONResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyJSONResponse, _requestTag);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }


            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyJSONResponse.isConnected(false, _requestTag);
        }
    }


    /**
     * =============================================================================================
     * JSON Array Request
     * =============================================================================================
     */


    /**
     * Volley Post/put/delete request without header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestMethod
     * @param _params
     * @param _requestTag
     * @param _volleyArrayResponse
     */
    public static void volleyJSONArrayRequest(final Context _context,
                                              boolean _isDialog,
                                              final View _view,
                                              String _url,
                                              int _requestMethod,
                                              final JSONArray _params,
                                              final String _requestTag,
                                              final GloxeyCallback.ArrayResponse _volleyArrayResponse) {


        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyArrayResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            JsonArrayRequest serverRequest = new JsonArrayRequest(_requestMethod, _url, _params, new com.android.volley.Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyArrayResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyArrayResponse, _requestTag);
                }
            });

            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyArrayResponse.isConnected(false, _requestTag);
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
     * @param _requestTag
     * @param _volleyArrayResponse
     */
    public static void volleyJSONArrayRequest(final Context _context,
                                              boolean _isDialog,
                                              final View _view,
                                              String _url,
                                              int _requestMethod,
                                              final JSONArray _params,
                                              final HashMap<String, String> _headers,
                                              final String _requestTag,
                                              final GloxeyCallback.ArrayResponse _volleyArrayResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyArrayResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            JsonArrayRequest serverRequest = new JsonArrayRequest(_requestMethod, _url, _params, new com.android.volley.Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyArrayResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyArrayResponse, _requestTag);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }
            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyArrayResponse.isConnected(false, _requestTag);

        }
    }

    /**
     * Volley Get request without header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _requestTag
     * @param _volleyArrayResponse
     */
    public static void volleyJSONArrayRequest(final Context _context,
                                              boolean _isDialog,
                                              final View _view,
                                              String _url,
                                              final String _requestTag,
                                              final GloxeyCallback.ArrayResponse _volleyArrayResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {

            /**
             * Set network true in callback
             */
            _volleyArrayResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            JsonArrayRequest serverRequest = new JsonArrayRequest(_url, new com.android.volley.Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyArrayResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyArrayResponse, _requestTag);
                }
            });
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);

        } else {

            /**
             * Set network false in callback
             */
            _volleyArrayResponse.isConnected(false, _requestTag);

        }
    }

    /**
     * Volley Get request with header
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _headers
     * @param _requestTag
     * @param _volleyArrayResponse
     */
    public static void volleyStringRequest(final Context _context,
                                           boolean _isDialog,
                                           final View _view,
                                           String _url,
                                           final HashMap<String, String> _headers,
                                           final String _requestTag,
                                           final GloxeyCallback.ArrayResponse _volleyArrayResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _volleyArrayResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */
            JsonArrayRequest serverRequest = new JsonArrayRequest(_url, new com.android.volley.Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    /**
                     * Set response in callback
                     */
                    _volleyArrayResponse.onResponse(response, _requestTag);
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
                    volleyErrorHandler(error, _volleyArrayResponse, _requestTag);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return _headers;
                }

            };
            AppController.getInstance(_context).addToRequestQueue(serverRequest, _requestTag);
        } else {
            /**
             * Set network false in callback
             */
            _volleyArrayResponse.isConnected(false, _requestTag);

        }
    }


    /**
     * Cancel Pending Request
     *
     * @param _context
     * @param _requestTag
     */
    public static void cancelPendingRequests(Context _context, String _requestTag) {
        AppController.getInstance(_context).cancelPendingRequests(_requestTag);
    }


    /**
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _params
     * @param _requestTag
     * @param _loopJResponse
     */
    public static void uploadMultiPartFiles(Context _context,
                                            boolean _isDialog,
                                            final View _view,
                                            String _url,
                                            RequestParams _params,
                                            final String _requestTag,
                                            final GloxeyCallback.LoopJMultiPartFileUpload _loopJResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _loopJResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */

            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(60000);

            client.post(_context, _url, _params, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);

                    _loopJResponse.onSuccess(statusCode, headers, bytes);

                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable error) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    _loopJResponse.onFailure(statusCode, headers, bytes, error);
                }

            });


        } else {
            /**
             * Set network false in callback
             */
            _loopJResponse.isConnected(false, _requestTag);
        }


    }


    /**
     * Set both the connection and socket timeouts. By default, both are set to
     * 60seconds.
     *
     * @param _context
     * @param _isDialog
     * @param _view
     * @param _url
     * @param _params
     * @param _requestTag
     * @param _timeOut
     * @param _loopJResponse
     */
    public static void uploadMultiPartFiles(Context _context,
                                            boolean _isDialog,
                                            final View _view,
                                            String _url,
                                            RequestParams _params,
                                            final String _requestTag,
                                            final int _timeOut,
                                            final GloxeyCallback.LoopJMultiPartFileUpload _loopJResponse) {

        /**
         * Check network before api call
         */
        if (isNetwork(_context)) {
            /**
             * Set network true in callback
             */
            _loopJResponse.isConnected(true, _requestTag);

            /**
             * Check whether we have to show loader or not
             */
            if (_isDialog) {
                showLoader(_context, _view);
            }

            /**
             * Handle network call
             */

            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(_timeOut);

            client.post(_context, _url, _params, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);

                    _loopJResponse.onSuccess(statusCode, headers, bytes);

                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable error) {

                    /**
                     * Hide loader
                     */
                    hideLoader(_view);
                    _loopJResponse.onFailure(statusCode, headers, bytes, error);
                }

            });


        } else {
            /**
             * Set network false in callback
             */
            _loopJResponse.isConnected(false, _requestTag);
        }


    }

    /***
     *
     * @param _error
     * @param _callback
     * @param _tag
     */
    private static void volleyErrorHandler(VolleyError _error, GloxeyCallback.StringResponse _callback, String _tag) {

        _callback.onErrorResponse(_error, true, _tag);


    }

    /**
     * @param _error
     * @param _callback
     * @param _tag
     */
    private static void volleyErrorHandler(VolleyError _error, GloxeyCallback.ArrayResponse _callback, String _tag) {

        _callback.onErrorResponse(_error, true, _tag);


    }

    /**
     * @param _error
     * @param _callback
     * @param _tag
     */
    private static void volleyErrorHandler(VolleyError _error, GloxeyCallback.JSONResponse _callback, String _tag) {

        _callback.onErrorResponse(_error, true, _tag);
    }


    /**
     * Check network connectivity
     *
     * @param context
     * @return
     */
    public static boolean isNetwork(@NonNull Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {

                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }


    /**
     * Get Name of connected network
     * Wifi or Mobile
     *
     * @param context
     * @return
     */
    public static String getConnectedNetworkName(@NonNull Context context) {

        String networkName = null;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    networkName = networkInfo.getTypeName();
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            networkName = anInfo.getTypeName();
                        }
                    }
                }
            }
        }

        return networkName;
    }

    /**
     * @param context
     * @return
     */
    public static boolean isWifiConnected(@NonNull Context context) {
        return isConnected(context, ConnectivityManager.TYPE_WIFI);
    }

    /**
     * @param context
     * @return
     */
    public static boolean isMobileConnected(@NonNull Context context) {
        return isConnected(context, ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * @param context
     * @param type
     * @return
     */
    private static boolean isConnected(@NonNull Context context, int type) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(type);
            return networkInfo != null && networkInfo.isConnected();
        } else {
            return isConnected(connMgr, type);
        }
    }

    /**
     * @param connMgr
     * @param type
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static boolean isConnected(@NonNull ConnectivityManager connMgr, int type) {
        Network[] networks = connMgr.getAllNetworks();
        NetworkInfo networkInfo;
        for (Network mNetwork : networks) {
            networkInfo = connMgr.getNetworkInfo(mNetwork);
            if (networkInfo != null && networkInfo.getType() == type && networkInfo.isConnected()) {
                return true;
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
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } else {
            _view.setVisibility(View.GONE);
        }

    }


}

