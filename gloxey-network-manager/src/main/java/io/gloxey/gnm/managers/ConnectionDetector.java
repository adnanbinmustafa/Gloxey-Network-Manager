package io.gloxey.gnm.managers;

public class ConnectionDetector {

    private ConnectionReceiverListener connectionReceiverListener;

    private static final ConnectionDetector ourInstance = new ConnectionDetector();

    public static ConnectionDetector getInstance() {
        return ourInstance;
    }

    private ConnectionDetector() {
    }

    public void setConnectionReceiverListener(ConnectionReceiverListener listener) {
        connectionReceiverListener = listener;
    }

    public ConnectionReceiverListener getConnectionReceiverListener() {
        return connectionReceiverListener;
    }


    public interface ConnectionReceiverListener {
        void isNetwork(boolean isConnected);

    }
}
