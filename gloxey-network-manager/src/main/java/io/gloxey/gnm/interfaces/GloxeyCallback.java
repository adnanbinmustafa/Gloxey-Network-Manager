package io.gloxey.gnm.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class GloxeyCallback {

    /**
     * Volley JSON Response
     */
    public static abstract class JSONResponse implements Callbacks {

        public void onResponse(String _response, String _tag) {
        }

        public void onResponse(JSONArray _response, String _tag) {
        }

        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
        }

        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable error) {
        }

    }

    /**
     * Volley String Response
     */
    public static abstract class StringResponse implements Callbacks {
        public void onResponse(JSONObject _response, String _tag) {
        }

        public void onResponse(JSONArray _response, String _tag) {
        }

        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
        }

        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable error) {
        }

    }

    /**
     * Volley Array Response
     */
    public static abstract class ArrayResponse implements Callbacks {
        public void onResponse(JSONObject _response, String _tag) {
        }

        public void onResponse(String _response, String _tag) {
        }

        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
        }

        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable error) {
        }
    }


    public static abstract class LoopJMultiPartFileUpload implements Callbacks {


        public void onResponse(JSONObject _response, String _tag) {
        }

        public void onResponse(JSONArray _response, String _tag) {
        }

        public void onResponse(String _response, String _tag) {
        }

        public void onErrorResponse(VolleyError _error, boolean _onErrorResponse, String _tag) {

        }

    }


    private interface Callbacks {

        void onResponse(JSONArray _response, String _tag);

        void onResponse(JSONObject _response, String _tag);

        void onResponse(String _response, String _tag);

        void isConnected(boolean _connected, String _tag);

        void onErrorResponse(VolleyError _error, boolean _onErrorResponse, String _tag);

        void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes);

        void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable error);

    }

}