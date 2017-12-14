package com.adroidtech.turnstr2.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpRequest extends AsyncTask<String, Void, String> {
    Request okRequest;
    String masterCall;
    private WeakReference<Context> context_weak;
    boolean showLoader = true;
    static ProgressDialog progress;
    AsyncCallback asyncCallback;
    private static int mRequestCount = 0;
    private SharedPreference sharedPreference;
    private String oldSessionId = "";

    public OkHttpRequest(Context context, String masterCall, AsyncCallback asyncCallback, Request request) {
        this.context_weak = new WeakReference<Context>(context);
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.okRequest = request;
    }

    public OkHttpRequest(Context context, String masterCall, AsyncCallback asyncCallback, Request request, boolean showLoader) {
        this.context_weak = new WeakReference<Context>(context);
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.okRequest = request;
        this.showLoader = showLoader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        sharedPreference = new SharedPreference(context_weak.get());
        if (Utils.isInternetAvailable(context_weak.get())) {
            if (showLoader) {
                Context context = context_weak.get();
                try {
                    if (progress == null) {
                        progress = new ProgressDialog(context);
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setMessage("Loading...");
                        progress.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String s = null;
        try {
            Context context = context_weak.get();
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build();
            Response response = client.newCall(okRequest).execute();
            s = response.body().string();
            Log.d("ANSWER", s);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            requestSocketTimeout();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                progress.dismiss();
                progress = null;
            } catch (Exception e1) {
            }
        }
        return s;
    }

    @Override
    protected void onPostExecute(String jObj) {
        super.onPostExecute(jObj);
        Context context = context_weak.get();
        try {
            if (jObj != null) {
                Log.i("Responce :" + masterCall, jObj);
                if (jObj.startsWith("{")) {
                    JSONObject jObject = new JSONObject(jObj);
                    if (jObject.has("errorCode")) {

                        try {
                            progress.dismiss();
                            progress = null;
                        } catch (Exception e) {
                        }
                        asyncCallback.getAsyncResult(jObj, masterCall);

                    } else {
                        try {
                            progress.dismiss();
                            progress = null;
                        } catch (Exception e) {
                        }
                        asyncCallback.getAsyncResult(jObj, masterCall);
                    }
                } else {
                    try {
                        progress.dismiss();
                        progress = null;
                    } catch (Exception e) {
                    }
                    asyncCallback.getAsyncResult(jObj, masterCall);
                }
            } else {
                JSONObject jObject = new JSONObject();
                jObject.put("errorMessage", "Sorry, An error occurred while completing your request");
                jObject.put("errorCode", "application error");
                try {
                    progress.dismiss();
                    progress = null;
                } catch (Exception e1) {
                }
                asyncCallback.getAsyncResult(jObject.toString(), masterCall);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                progress.dismiss();
                progress = null;
            } catch (Exception e1) {
            }
            asyncCallback.getAsyncResult(jObj, masterCall);
        }
    }

    public void requestSocketTimeout() {
        try {
            Context context = context_weak.get();
            if (mRequestCount < 1) {
                mRequestCount++;
                if (okRequest != null)
                    new OkHttpRequest(context, masterCall, asyncCallback, okRequest).execute();
            } else {
                mRequestCount = 0;
                try {
                    progress.dismiss();
                    progress = null;
                } catch (Exception e1) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            progress.dismiss();
            progress = null;
        }
    }

}