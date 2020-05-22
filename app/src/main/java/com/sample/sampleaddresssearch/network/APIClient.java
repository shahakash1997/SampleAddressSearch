package com.sample.sampleaddresssearch.network;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sample.sampleaddresssearch.config.Config;
import com.sample.sampleaddresssearch.utils.Tracer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String TAG = Config.logger + "DelhiveryClient";
    private static APIClient instance;
    private static OkHttpClient client;

    private APIClient() {
        Tracer.debug(TAG, " APIClient" + " ");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        client = httpClient
                .connectTimeout(Config.API_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.API_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Config.API_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public static APIClient getInstance() {
        if (instance == null)
            instance = new APIClient();
        return instance;
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.ADDRESS_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client).build();
    }

    public ApiService getRetrofitAdapter() {
        return getRetrofit().create(ApiService.class);
    }
}
