package io.gloxey.interfaces;

import com.google.gson.GsonBuilder;

import io.gloxey.constants.Constants;
import io.gloxey.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroApiInterface {

   
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    class ApiClient {

        static Retrofit retrofitBase = null;

        /**
         * Retrofit client singelton
         *
         * @return
         */
        public static RetroApiInterface getClient() {

            if (retrofitBase == null) {
                retrofitBase = new Retrofit.Builder()
                        .baseUrl(Constants.ApiLink.SERVER_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                        .build();
            }
            return retrofitBase.create(RetroApiInterface.class);
        }

    }

}
