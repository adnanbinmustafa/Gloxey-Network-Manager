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

import com.android.volley.VolleyError;

import io.gloxey.R;
import io.gloxey.apis.Apis;
import io.gloxey.gnm.interfaces.VolleyCallback;
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

        Apis.getWeatherRecord(this, isDialog, progressBar, new VolleyCallback.StringResponse() {
            @Override
            public void onResponse(String _response, String _tag) {

                tvResult.setText("" + _response);

                showSnackBar(parentLayout, "Network call finished.");

            }

            @Override
            public void isConnected(boolean _connected, String _tag) {
                if (!_connected) {
                    showSnackBar(parentLayout, getString(R.string.internet_not_found), getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getWeatherRecordWithVolley();
                        }
                    });
                }
            }

            @Override
            public void onTimeoutError(VolleyError _error, boolean _timeOutError, String _tag) {

                if (_timeOutError) {
                    showSnackBar(parentLayout, getString(R.string.internet_not_found), getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getWeatherRecordWithVolley();
                        }
                    });
                }
            }

            @Override
            public void onNetworkError(VolleyError _error, boolean _onNetworkError, String _tag) {

            }

            @Override
            public void onAuthFailureError(VolleyError _error, boolean _onAuthFailureError, String _tag) {

            }

            @Override
            public void onParseError(VolleyError _error, boolean _onParseError, String _tag) {

            }

            @Override
            public void onErrorResponse(VolleyError _error, boolean _onErrorResponse, String _tag) {

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
