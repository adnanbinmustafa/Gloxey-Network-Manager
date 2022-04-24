package io.gloxey.gnm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.gloxey.gnm.managers.ConnectionDetector;

public class NetworkConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (ConnectionDetector.getInstance().getConnectionReceiverListener() != null) {
            ConnectionDetector.getInstance().getConnectionReceiverListener().isNetwork(isConnected);
        }

    }
}