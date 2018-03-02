package com.adroidtech.turnstr2.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.adroidtech.turnstr2.Utils.CommanFunctions;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;


public class CommonAsync extends AsyncTask<String, Void, String> {
    boolean showLoader = true;
    static ProgressDialog progress;
    HashMap<String, String> extraHeaders;
    String request_type;
    String app_token;
    Context context;
    JSONObject jsonObject;
    String masterCall;
    AsyncCallback asyncCallback;

    public CommonAsync() {
    }

    private SharedPreference sharedPreference;
    private static int mRequestCount = 0;

    //IAS prelims exam apitude leacture
    public CommonAsync(Context context, JSONObject jsonObject, AsyncCallback asyncCallback, String masterCall) {
        this.context = context;
        this.jsonObject = jsonObject;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
    }

    public CommonAsync(Context context, JSONObject jsonObject, AsyncCallback asyncCallback, String masterCall, HashMap<String, String> extraHeaders) {
        this.context = context;
        this.jsonObject = jsonObject;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.extraHeaders = extraHeaders;
    }


    public CommonAsync(Context context, JSONObject jsonObject, AsyncCallback asyncCallback, String masterCall, String app_token) {
        this(context, jsonObject, asyncCallback, masterCall);
        this.app_token = app_token;
    }

    public CommonAsync(Context context, String request_type, AsyncCallback asyncCallback, String masterCall, JSONObject jsonObject) {
        this.context = context;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.request_type = request_type;
        this.jsonObject = jsonObject;
    }

    public CommonAsync(Context context, String request_type, AsyncCallback asyncCallback, String masterCall, JSONObject jsonObject, HashMap<String, String> extraHeaders) {
        this.context = context;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.request_type = request_type;
        this.extraHeaders = extraHeaders;
        this.jsonObject = jsonObject;
    }

    public CommonAsync(Context context, boolean isShowLoader, String request_type, AsyncCallback asyncCallback, String masterCall, JSONObject jsonObject, HashMap<String, String> extraHeaders) {
        this.context = context;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.request_type = request_type;
        this.extraHeaders = extraHeaders;
        this.jsonObject = jsonObject;
        this.showLoader=isShowLoader;
    }


    public CommonAsync(Context context, JSONObject jsonObject, String masterCall, AsyncCallback asyncCallback, HashMap<String, String> extraHeaders, String request_type, String app_token) {
        this.context = context;
        this.jsonObject = jsonObject;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
        this.extraHeaders = extraHeaders;
        this.request_type = request_type;
        this.app_token = app_token;
    }

    public CommonAsync(Context context, AsyncCallback asyncCallback, String masterCall) {
        this.context = context;
        this.jsonObject = jsonObject;
        this.masterCall = masterCall;
        this.asyncCallback = asyncCallback;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (Utils.isInternetAvailable(context)) {
            if (showLoader) {
                try {
                    if (progress == null) {
                        progress = new ProgressDialog(context);
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setMessage("Please wait...");
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
        Log.e("Time Count: ", Calendar.getInstance().getTimeInMillis() + "");
        WebApi webApi = WebApi.getInstance();
        String s = null;
        try {
            if (extraHeaders != null) {
                String auth = extraHeaders.get("auth_token");
                extraHeaders.put("Authorization", auth);
                extraHeaders.put("auth-token", auth);
            }
            if ((request_type != null) && request_type.equals("GET")) {
                s = webApi.webCallForGet(context, jsonObject, masterCall, extraHeaders);
            } else if ((request_type != null) && request_type.equals("PUT")) {
                s = webApi.webCallForPutRequest(context, jsonObject, masterCall, extraHeaders);
            } else if ((request_type != null) && request_type.equals("DELETE")) {
                s = webApi.webCallForDeleteRequest(context, jsonObject, masterCall, extraHeaders);
            } else {
                s = webApi.webCallForPost(context, jsonObject, masterCall, extraHeaders);
                Log.e("data API", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    protected void onPostExecute(String jObj) {
        super.onPostExecute(jObj);
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
                ExceptionHandling();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandling();
        }

    }

    public void ExceptionHandling() {
        try {
            JSONObject jObject = new JSONObject();
            jObject.put("errorMessage", "Sorry, An error occurred while completing your request");
            jObject.put("errorCode", "application error");
            try {
                progress.dismiss();
                progress = null;
            } catch (Exception e1) {
            }
            asyncCallback.getAsyncResult(jObject.toString(), masterCall);
        } catch (JSONException e) {
        }
    }


}