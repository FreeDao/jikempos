package com.jike.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.buybal.framework.constant.Constant;

public class NetChangeReceiver extends BroadcastReceiver {
    public static final String TAG = NetChangeReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo mobiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        SharedPreferences.Editor editor = prefs.edit();
        if (netInfo != null && netInfo.isAvailable()) {
            if (wifiNetInfo.isAvailable() && wifiNetInfo.isConnected()) {
                editor.putInt(Constant.NET_STATUS_XML_KEY, Constant.NET_STATUS_WIFI);
            } else if (mobiNetInfo.isAvailable() && mobiNetInfo.isConnected()) {
                editor.putInt(Constant.NET_STATUS_XML_KEY, Constant.NET_STATUS_MOBI);
            }
        } else {
            editor.putInt(Constant.NET_STATUS_XML_KEY, Constant.NET_STATUS_OFFLINE);
        }
        editor.commit();
    }

}
