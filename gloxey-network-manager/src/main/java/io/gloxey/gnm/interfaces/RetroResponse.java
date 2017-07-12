package io.gloxey.gnm.interfaces;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Callback with generic Type to handle any class.
 */
public interface RetroResponse {


    <T> void onResponse(Call<T> _call, Response<T> _response);

    <T> void onFailure(Call<T> _call, Throwable _t);

    void isNetwork(boolean connected);
}
