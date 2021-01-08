package com.wdp.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheck {
	Context context;

	public NetworkCheck(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;

	}
	
	public  static  boolean isConnected(Context context)
	{
		Boolean flag=false;
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mMobileNewtork = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		if (mWifi.isConnected() || mMobileNewtork.isConnected()) {
		    // Do whatever
			//Toast.makeText(getApplicationContext(), "Connected ",Toast.LENGTH_SHORT).show();
			
			flag=true;
		}
			
		return flag;
		
	}
}
