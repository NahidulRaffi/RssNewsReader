package com.androhungers.rssnewsreader.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androhungers.rssnewsreader.api.ApiServices;
import com.androhungers.rssnewsreader.common.RetrofitSingleton;
import com.androhungers.rssnewsreader.model.signin.SignRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRepository {

    ApiServices apiServices;

    public ApiRepository() {
        apiServices = RetrofitSingleton.getInstance().getRetrofit().create(ApiServices.class);
    }

    public LiveData<SigninResponseModel> loginUser(SignRequestModel request) {
        final MutableLiveData<SigninResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody userName = RequestBody.create(MediaType.parse("multipart/form-data"), request.getUser_name());
        RequestBody password = RequestBody.create(MediaType.parse("multipart/form-data"), request.getPassword());


        Log.d("SearchKeyRequest", s);
        apiServices.signInUser(userName,password).enqueue(new Callback<SigninResponseModel>() {
            @Override
            public void onResponse(Call<SigninResponseModel> call, Response<SigninResponseModel> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<SigninResponseModel> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }
}

