package com.androhungers.rssnewsreader.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.*;

public class RetrofitSingleton {
    private Retrofit retrofit = null;

    private static final RetrofitSingleton ourInstance = new RetrofitSingleton();

    public static RetrofitSingleton getInstance() {
        return ourInstance;
    }

    public RetrofitSingleton() {

    }

    public Retrofit getRetrofit(){
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(1000, TimeUnit.SECONDS)
                    .readTimeout(1000, TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
