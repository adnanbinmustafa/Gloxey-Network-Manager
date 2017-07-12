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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import io.gloxey.R;
import io.gloxey.apis.Apis;
import io.gloxey.gnm.interfaces.VolleyResponse;
import io.gloxey.gnm.managers.ConnectionDetector;

public class MainActivity extends AppCompatActivity implements ConnectionDetector.ConnectionReceiverListener {

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
    protected void onResume() {
        ConnectionDetector.getInstance().setConnectionReceiverListener(this);
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


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


        if (isProgressBar) {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
        } else {
            progressBar = null;
        }

        Apis.getWeatherRecord(this, isDialog, progressBar, new VolleyResponse() {
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


    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackBar(View view, String message, String actionText, View.OnClickListener onClickListener) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionText, onClickListener).show();
    }


    @Override
    public void isNetwork(boolean isConnected) {

        if (isConnected) {
            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}
