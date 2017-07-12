package io.gloxey.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import io.gloxey.R;
import io.gloxey.apis.Apis;
import io.gloxey.constants.Constants;
import io.gloxey.gnm.interfaces.RetroResponse;
import io.gloxey.gnm.interfaces.VolleyResponse;
import io.gloxey.gnm.managers.ConnectionManager;
import io.gloxey.interfaces.RetroApiInterface;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private CoordinatorLayout parentLayout;
    private ProgressBar progressBar;
    private boolean isDialog = true;
    private boolean isProgressBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBasicViews();
    }

    private void setBasicViews() {

        tvResult = (TextView) findViewById(R.id.tvResult);
        parentLayout = (CoordinatorLayout) findViewById(R.id.parentLayout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu _menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, _menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_retro_request:
                getWeatherRecordWithRetrofit();
                break;
            case R.id.menu_volley_request:
                tvResult.setText("");
                isDialog = true;
                isProgressBar = false;
                getWeatherRecordWithVolley();
                break;
            case R.id.menu_volley_request_no_dialog:
                tvResult.setText("");
                isProgressBar = false;
                isDialog = false;
                getWeatherRecordWithVolley();
                break;
            case R.id.menu_volley_request_with_progress_bar:
                tvResult.setText("");
                isProgressBar = true;
                isDialog = true;
                getWeatherRecordWithVolley();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    private void getWeatherRecordWithVolley() {

        String city = "London,uk";

        if (isProgressBar) {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
        } else {
            progressBar = null;
        }

        Apis.getWeatherRecord(this, isDialog, progressBar, city, new VolleyResponse() {
            @Override
            public void onResponse(String _response) {

                /**
                 * Handle response
                 */
                try {
                    /**
                     * Bonus ;)
                     */
//                    WeatherResponse weatherResponse = GloxeyJsonParser.getInstance().parse(_response, WeatherResponse.class);

                    tvResult.setText("" + _response);

                    showSnackBar(parentLayout, "Network call finished.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

                /**
                 * Error handling
                 */
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    showSnackBar(parentLayout, getString(R.string.internet_not_found), getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getWeatherRecordWithVolley();
                        }
                    });

                } else if (error instanceof AuthFailureError) {
                } else if (error instanceof ServerError) {
                } else if (error instanceof NetworkError) {
                } else if (error instanceof ParseError) {
                }


            }

            @Override
            public void isNetwork(boolean connected) {

                /**
                 * if internet is not connected then display
                 * SnackBar to handle retry
                 */
                if (!connected) {
                    showSnackBar(parentLayout, getString(R.string.internet_not_found), getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getWeatherRecordWithVolley();
                        }
                    });
                }
            }
        });
    }


    public void getWeatherRecordWithRetrofit() {


        RetroApiInterface apiService = RetroApiInterface.ApiClient.getClient();

        ConnectionManager.retroRequest(this, true, null, apiService.getTopRatedMovies(Constants.ApiLink.APP_ID), new RetroResponse() {
            @Override
            public <T> void onResponse(Call<T> _call, Response<T> _response) {

                tvResult.setText(_response.toString());
            }

            @Override
            public <T> void onFailure(Call<T> _call, Throwable _t) {
                /**
                 * handle retro errors
                 */
                showSnackBar(parentLayout, "Please try again later.");
            }

            @Override
            public void isNetwork(boolean connected) {
                /**
                 * if internet is not connected then display
                 * SnackBar to handle retry
                 */
                if (!connected) {
                    showSnackBar(parentLayout, getString(R.string.internet_not_found), getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getWeatherRecordWithVolley();
                        }
                    });
                }
            }
        });


    }

    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackBar(View view, String message, String actionText, View.OnClickListener onClickListener) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionText, onClickListener).show();
    }


}
