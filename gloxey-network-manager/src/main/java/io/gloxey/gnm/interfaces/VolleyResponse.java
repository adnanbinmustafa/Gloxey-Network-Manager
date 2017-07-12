package io.gloxey.gnm.interfaces;


import com.android.volley.VolleyError;


/**
 * Callback with generic Type to handle any class.
 */
public interface VolleyResponse {

    void onResponse(String _response);

    void onErrorResponse(VolleyError error);

    void isNetwork(boolean connected);

}
