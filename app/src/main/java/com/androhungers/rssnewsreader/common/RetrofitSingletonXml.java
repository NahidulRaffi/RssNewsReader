package com.androhungers.rssnewsreader.common;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitSingletonXml {
    private Retrofit retrofit = null;

    private static final RetrofitSingletonXml ourInstance = new RetrofitSingletonXml();

    public static RetrofitSingletonXml getInstance() {
        return ourInstance;
    }

    public RetrofitSingletonXml() {

    }

    public Retrofit getRetrofitXml(String url){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit;
    }
}
