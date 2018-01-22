package com.adroidtech.turnstr2.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.ArrayMap;
import android.util.Log;

import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpRequestSender extends AsyncTask<String, Void, String> {
    private final String app_token;
    private final String url;
    ArrayMap<String, String> formField;
    ArrayMap<String, File> filePart;
    Request okRequest;
    String masterCall;
    private WeakReference<Context> context_weak;
    boolean showLoader = true;
    static ProgressDialog progress;
    AsyncCallback asyncCallback;
    private static int mRequestCount = 0;
    private SharedPreference sharedPreference;
    private String oldSessionId = "";
    private String request_type;

    public OkHttpRequestSender(Context context, AsyncCallback asyncCallback, String url, ArrayMap<String, String> formField, ArrayMap<String,
            File> filePart, String app_token, String request_type) {
        this.context_weak = new WeakReference<Context>(context);
        this.asyncCallback = asyncCallback;
        this.formField = formField;
        this.filePart = filePart;
        this.url = url;
        this.app_token = app_token;
        this.request_type = request_type;
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
        String responce = null;
        try {
            Context context = context_weak.get();
            if ((request_type != null) && request_type.equals("POST")) {
                responce = new OkHttp3Helper(context).postMultiPartToServer(url, formField, filePart, app_token);
            } else if ((request_type != null) && request_type.equals("PUT")) {
                responce = new OkHttp3Helper(context).putMultiPartToServer(url, formField, filePart, app_token);
            } else if ((request_type != null) && request_type.equals("GET")) {
            }
            Log.d("responce : ", responce);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                progress.dismiss();
                progress = null;
            } catch (Exception e1) {
            }
        }
        return responce;
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

}