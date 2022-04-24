package com.example.gloxeynetworkmanagerexample.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.gloxeynetworkmanagerexample.R;
import com.example.gloxeynetworkmanagerexample.apis.Apis;
import com.google.android.material.snackbar.Snackbar;

import io.gloxey.gnm.interfaces.GloxeyCallback;
import io.gloxey.gnm.managers.ConnectionDetector;

public class MainActivity extends AppCompatActivity implements ConnectionDetector.ConnectionReceiverListener {

    private TextView tvResult;
    private LinearLayout parentLayout;
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
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);

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


        Apis.getApiRecord(this, isDialog, progressBar, new GloxeyCallback.StringResponse() {
            @Override
            public void onResponse(String _response, String _tag) {

                tvResult.setText("Api used:\nhttps://api.publicapis.org/entries \n\n" + _response);

                showSnackBar(parentLayout, "Network call finished.");

            }

            @Override
            public void isConnected(boolean _connected, String _tag) {
                if (!_connected) {
                    showSnackBar(parentLayout, getString(R.string.internet_not_found), getString(R.string.retry), view -> getWeatherRecordWithVolley());
                }
            }


            @Override
            public void onErrorResponse(VolleyError _error, boolean _onErrorResponse, String _tag) {
                showSnackBar(parentLayout, _error.getMessage());

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