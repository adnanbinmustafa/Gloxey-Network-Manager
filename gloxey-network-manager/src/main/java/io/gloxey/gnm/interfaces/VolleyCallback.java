package io.gloxey.gnm.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyCallback {

    /**
     * Volley JSON Response
     */
    public static abstract class JSONResponse implements Callbacks {

        public void onResponse(String _response, String _tag) {
        }

        public void onResponse(JSONArray _response, String _tag) {
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

    }

    /**
     * Volley Array Response
     */
    public static abstract class ArrayResponse implements Callbacks {
        public void onResponse(JSONObject _response, String _tag) {
        }

        public void onResponse(String _response, String _tag) {
        }

    }

    private interface Callbacks {

        void onResponse(JSONArray _response, String _tag);

        void onResponse(JSONObject _response, String _tag);

        void onResponse(String _response, String _tag);

        void isConnected(boolean _connected, String _tag);


        void onTimeoutError(VolleyError _error, boolean _timeOutError, String _tag);

        void onNetworkError(VolleyError _error, boolean _onNetworkError, String _tag);

        void onAuthFailureError(VolleyError _error, boolean _onAuthFailureError, String _tag);

        void onParseError(VolleyError _error, boolean _onParseError, String _tag);

        void onErrorResponse(VolleyError _error, boolean _onErrorResponse, String _tag);
        

    }

}