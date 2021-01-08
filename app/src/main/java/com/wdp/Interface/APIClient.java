package com.wdp.Interface;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {
    private static Retrofit retrofit = null;
    private static long CONNECTION_TIMEOUT = 100;
    private static String RETROFIT_LOGGER = "Result";
    public static Retrofit getClient() {

        OkHttpClient client = getOkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(RETROFIT_LOGGER, message);
            }
        });

        // HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        //okClientBuilder.addNetworkInterceptor(new StethoInterceptor());

        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        return okClientBuilder.build();
    }
}

