package com.adroidtech.turnstr2.WebServices;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.adroidtech.turnstr2.Utils.GeneralValues;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sarbjot singh on 9/22/2016.
 */
public class WebApi {
    private static WebApi webApi;
    InputStream is;
    OutputStream os;
    HttpURLConnection conn;
    static final String COOKIES_HEADER = "Set-Cookie";
    static final String COOKIE = "Cookie";
    String jsonResponse;
    String Common_URL;
    private String jsonError;

    public static final WebApi getInstance() {
        if (webApi == null) {
            webApi = new WebApi();
        }
        return webApi;
    }

    //===========GET Method===========

    public String webCallForGet(Context context, JSONObject jsonObject, String masterRequestType, HashMap<String, String> extraHeaders) {
        Common_URL = "";
        String url = GeneralValues.BASE_URL + masterRequestType;
        Log.e("TAG", "WebApi 60"+url);
        Common_URL = appendGetFeildsToUrl(url, jsonObject);
        StringBuffer response = new StringBuffer();
        try {
            Log.e("Request-" + masterRequestType, Common_URL);
            URL url_Connection = new URL(Common_URL);
            HttpURLConnection conn = (HttpURLConnection) url_Connection.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setRequestProperty("auth_token",extraHeaders.get("auth_token"));
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");
            conn.setConnectTimeout(60 * 1000);
            int status = conn.getResponseCode();
            conn.setInstanceFollowRedirects(true);
            InputStreamReader inputStreamReader = null;
            if (status == 200) {
                inputStreamReader = new InputStreamReader(conn.getInputStream());
            } else {
                inputStreamReader = new InputStreamReader(conn.getErrorStream());
            }
            BufferedReader reader = new BufferedReader(inputStreamReader);
            int line;
            while ((line = reader.read()) != -1) {
                response.append((char) line);
            }
            inputStreamReader.close();
            conn.disconnect();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("Error Message", response.toString());
        }
        Log.i("Response-" + masterRequestType, response.toString());
        return response.toString();
    }

    private String appendGetFeildsToUrl(String url, JSONObject jsonObject) {
        if ((jsonObject != null) && (jsonObject.length() > 0)) {
            Iterator<String> allKeys = jsonObject.keys();
            url = url + "?";
            Log.e("TAG", "WebApi 102"+url);
            while (allKeys.hasNext()) {
                String key = (String) allKeys.next();
                String value = jsonObject.optString(key);
                url += key + "=" + value + "&";
            }
        }
        Log.e("TAG", "........url..."+url);

        return url;//url.substring(0, url.length() - 1);
    }


    //===========Post Method===========

    public String webCallForPost(Context context, JSONObject jsonObject, String masterRequestType, HashMap<String, String> extraHeaders) throws IOException {
        Common_URL = GeneralValues.BASE_URL + masterRequestType;
        try {
            jsonResponse = "";
            //constants
            Log.e("Request-" + masterRequestType, Common_URL);
            Log.e("Request-", jsonObject.toString());
            URL url = new URL(Common_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            addHeadersParm(conn, extraHeaders);
            //add json data to request connection
            String message = jsonObject.toString();
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            //make some HTTP header nicety
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();

            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            os.flush();
            int responseCode = conn.getResponseCode(); //can call this instead of con.connect()
            if (responseCode >= 400 && responseCode <= 499) {
                InputStream error = conn.getErrorStream();
                jsonResponse = streamToString(error);
            } else {
                is = conn.getInputStream();
                jsonResponse = streamToString(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        Log.i("Response-" + masterRequestType, jsonResponse);
        return jsonResponse;
    }


    public String webCallForPutRequest(Context context, JSONObject jsonObject, String masterRequestType, HashMap<String, String> extraHeaders) throws IOException {
        Common_URL = GeneralValues.BASE_URL + masterRequestType;
        try {
            jsonResponse = "";
            //constants
            Log.i("Request-" + masterRequestType, Common_URL);
            URL url = new URL(Common_URL);

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000 /* milliseconds */);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            addHeadersParm(conn, extraHeaders);
            //add json data to request connection
            String message = jsonObject.toString();
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            //make some HTTP header nicety
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            os.flush();
            int responseCode = conn.getResponseCode(); //can call this instead of con.connect()
            if (responseCode >= 400 && responseCode <= 499) {
                InputStream error = conn.getErrorStream();
                jsonResponse = streamToString(error);
            } else {
                is = conn.getInputStream();
                jsonResponse = streamToString(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        Log.i("Response-" + masterRequestType, jsonResponse);
        return jsonResponse;
    }

    public String webCallForDeleteRequest(Context context, JSONObject jsonObject, String masterRequestType, HashMap<String, String> extraHeaders) throws IOException {
        Common_URL = GeneralValues.BASE_URL + masterRequestType;
        try {
            jsonResponse = "";
            //constants
            Log.i("Request-" + masterRequestType, Common_URL);
            URL url = new URL(Common_URL);

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000 /* milliseconds */);
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            addHeadersParm(conn, extraHeaders);
            //add json data to request connection
            String message = jsonObject.toString();
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            //make some HTTP header nicety
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            os.flush();
            int responseCode = conn.getResponseCode(); //can call this instead of con.connect()
            if (responseCode >= 400 && responseCode <= 499) {
                InputStream error = conn.getErrorStream();
                jsonResponse = streamToString(error);
            } else {
                is = conn.getInputStream();
                jsonResponse = streamToString(is);
            }
//            Map<String, List<String>> headerFields = conn.getHeaderFields();
//            List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
//            if (cookiesHeader != null) {
//                for (String cookie : cookiesHeader) {
//                    msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        Log.i("Response-" + masterRequestType, jsonResponse);
        return jsonResponse;
    }

    private void addHeadersParm(HttpURLConnection conn, HashMap<String, String> extraHeaders) {
        if ((extraHeaders != null) && (extraHeaders.size() > 0)) {
            Set<String> allKeys = extraHeaders.keySet();
            for (String key : allKeys) {
                System.out.println(key);
                conn.setRequestProperty(key, extraHeaders.get(key));
            }
        }
    }

    public InputStream connectionEstablished(String mUrl, MultipartEntity multipartEntity, String app_token) {
        InputStream mInputStreamis = null;
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 600000); // Timeout
        HttpResponse response;
        try {
            HttpPost post = new HttpPost(mUrl);
            post.setEntity(multipartEntity);
            if ((app_token != null) && (!app_token.equals(""))) {
                post.setHeader("Authorization", app_token);
            }
            response = client.execute(post);
            if (response != null) {
                mInputStreamis = response.getEntity().getContent(); // Get the
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mInputStreamis;

    }

    public String streamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public String converResponseToString(InputStream stream) {
        String mResult = "";
        StringBuilder mStringBuilder;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream, "UTF8"), 8);
            mStringBuilder = new StringBuilder();
            mStringBuilder.append(reader.readLine() + "\n");
            String line = "0";
            while ((line = reader.readLine()) != null) {
                mStringBuilder.append(line + "\n");
            }
            stream.close();
            mResult = mStringBuilder.toString();

            return mResult;
        } catch (Exception e) {
            return mResult;
        }
    }



    public static InputStream openINputStreamConnection(String urlS) {
        try {
            URL url = new URL(urlS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream stream;
            try {
                conn.setDoInput(true);
                conn.setDoOutput(true);
                stream = conn.getInputStream();
                return stream;
            } catch (Exception e) {
                e.printStackTrace();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}