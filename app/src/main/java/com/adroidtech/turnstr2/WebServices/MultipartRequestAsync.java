package com.adroidtech.turnstr2.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.adroidtech.turnstr2.Utils.GeneralValues;

import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 *
 */
public class MultipartRequestAsync extends AsyncTask<Void, Void, JSONObject>{
    boolean showLoader = true;
    ProgressDialog progress;
    public String url;
    public MultipartAsyncCallback asyncCallback;
    public WebApi sSetconnection;
    String mResult = null;
    InputStream mInputStreamis = null;
    int checkResponse = 0;
    MultipartEntity multipartEntity;
    Context context;
    String app_token;

    public MultipartRequestAsync(Context context, MultipartEntity multipartEntity, MultipartAsyncCallback asyncCallback, String url) {
        this.context = context;
        this.sSetconnection = new WebApi();
        this.multipartEntity = multipartEntity;
        this.asyncCallback = asyncCallback;
        this.url = url;
    }

    public MultipartRequestAsync(Context context, MultipartEntity multipartEntity, MultipartAsyncCallback asyncCallback, String url, String app_token) {
        this(context, multipartEntity, asyncCallback, url);
        this.app_token = app_token;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            multipartEntity.writeTo(bytes);
            String content = bytes.toString();
            System.out.println("My........." + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (showLoader) {
            progress = new ProgressDialog(context);
            progress.setCancelable(false);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setMessage("Please wait...");
            progress.show();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... arg0) {
        String mUrl;
//        mUrl = GeneralValues.Image_UPLOAD_TO_SERVER;
        JSONObject jObj = null;
        try {
            String urlMain = GeneralValues.BASE_URL + url;
            mInputStreamis = sSetconnection.connectionEstablished(urlMain, multipartEntity, app_token);
            if (mInputStreamis != null) {
                mResult = sSetconnection.converResponseToString(mInputStreamis);

                if (!(mResult.equals(""))) {
                    jObj = new JSONObject(mResult);
                } else {
                    checkResponse = 3;
                }
            } else {
                checkResponse = 2;
            }
        } catch (Exception e) {
            checkResponse = 3;
            e.printStackTrace();

        }
        return jObj;
    }


    protected void onPostExecute(JSONObject jObj) {
        progress.dismiss();
        try {
            if (jObj == null) {
                jObj.put("status", "error");
                jObj.put("message", "Problem with Web services");
            } else {
                Log.i("Result", jObj + "");
                String status = jObj.get("status").toString();
                if (status.equals("expired")) {
                    //  CommanFunction.autoLoginDialog(context, this);
                }
            }
//            expired

            asyncCallback.getMultipartAsyncResult(jObj, url);
        } catch (Exception e) {
            e.printStackTrace();
            asyncCallback.getMultipartAsyncResult(jObj, url);
        }


    }

}