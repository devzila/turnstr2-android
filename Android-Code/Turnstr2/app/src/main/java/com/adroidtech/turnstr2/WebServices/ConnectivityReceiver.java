package com.adroidtech.turnstr2.WebServices;

/**
 * Created by softobiz on 11/3/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.adroidtech.turnstr2.Utils.CommanFunctions;
import com.adroidtech.turnstr2.Utils.Utils;

public class ConnectivityReceiver extends BroadcastReceiver {

    public static NetworkListener networkListener;


    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            if (!isConnected) {
                try {
                    Utils.networkDialog(CommanFunctions.getActivity());
                } catch (Exception e) {
                }
            } else {
                try {
                    Utils.networkDialogDismiss();
                } catch (Exception e) {
                }
            }
            if (networkListener != null) {
                networkListener.onNetworkConnectionChanged(isConnected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public interface NetworkListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}