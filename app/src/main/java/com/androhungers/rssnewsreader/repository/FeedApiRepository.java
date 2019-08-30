package com.androhungers.rssnewsreader.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androhungers.rssnewsreader.api.ApiServices;
import com.androhungers.rssnewsreader.common.RetrofitSingleton;
import com.androhungers.rssnewsreader.common.RetrofitSingletonXml;
import com.androhungers.rssnewsreader.model.addRss.AddRssRequestModel;
import com.androhungers.rssnewsreader.model.addRss.AddRssResponseModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssRequestModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssResponseModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssRequestModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssResponseModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssRequestModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssResponseModel;
import com.androhungers.rssnewsreader.model.rssFeed.Feed;
import com.androhungers.rssnewsreader.model.signin.SigninRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.androhungers.rssnewsreader.model.signup.SignupRequestModel;
import com.androhungers.rssnewsreader.model.signup.SignupResponseModel;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedApiRepository {

    ApiServices apiServices;

    public FeedApiRepository(String url) {
        apiServices = RetrofitSingletonXml.getInstance().getRetrofitXml(url).create(ApiServices.class);
    }

    public LiveData<Feed> getRssFeed(String request) {
        final MutableLiveData<Feed> data =  new MutableLiveData();

        Log.d("SearchRequest", request);
        apiServices.getRssFeed(request).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }


}

