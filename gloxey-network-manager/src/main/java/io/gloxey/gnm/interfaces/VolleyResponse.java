package io.gloxey.gnm.interfaces;


import com.android.volley.VolleyError;

import org.json.JSONObject;


/**
 * Callback with generic Type to handle any class.
 */
public class VolleyResponse<T> {


    public void onResponse(String response) {

    }

    public void onResponse(JSONObject response) {

    }

    public void onErrorResponse(VolleyError error) {

    }

    public void isNetwork(boolean connected) {

    }


}


//public class VolleyResponse<T> {
//
//
//    /**
//     * Callback interface for delivering parsed responses.
//     */
//    public interface Listener<T> {
//        /**
//         * Called when a response is received.
//         */
//        public void onResponse(T response);
//    }
//
//    /**
//     * Callback interface for delivering error responses.
//     */
//    public interface ErrorListener {
//        /**
//         * Callback method that an error has been occurred with the
//         * provided error code and optional user-readable message.
//         */
//        public void onErrorResponse(VolleyError error);
//
//        public void isNetwork(boolean connected);
//
//    }
//}
