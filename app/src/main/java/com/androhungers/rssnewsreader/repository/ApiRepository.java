package com.androhungers.rssnewsreader.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androhungers.rssnewsreader.api.ApiServices;
import com.androhungers.rssnewsreader.common.RetrofitSingleton;
import com.androhungers.rssnewsreader.model.addRss.AddRssRequestModel;
import com.androhungers.rssnewsreader.model.addRss.AddRssResponseModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssRequestModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssResponseModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssRequestModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssResponseModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssRequestModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssResponseModel;
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

public class ApiRepository {

    ApiServices apiServices;

    public ApiRepository() {
        apiServices = RetrofitSingleton.getInstance().getRetrofit().create(ApiServices.class);
    }

    public LiveData<SigninResponseModel> loginUser(SigninRequestModel request) {
        final MutableLiveData<SigninResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody userName = RequestBody.create(MediaType.parse("multipart/form-data"), request.getUser_name());
        RequestBody password = RequestBody.create(MediaType.parse("multipart/form-data"), request.getPassword());


        Log.d("SearchRequest", s);
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

    public LiveData<SignupResponseModel> signUpUser(SignupRequestModel request) {
        final MutableLiveData<SignupResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody userName = RequestBody.create(MediaType.parse("multipart/form-data"), request.getUser_name());
        RequestBody password = RequestBody.create(MediaType.parse("multipart/form-data"), request.getPassword());
        RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"), request.getName());
        RequestBody age = RequestBody.create(MediaType.parse("multipart/form-data"), request.getAge());


        Log.d("SearchRequest", s);
        apiServices.signUpUser(userName,password,name,age).enqueue(new Callback<SignupResponseModel>() {
            @Override
            public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<SignupResponseModel> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }

    public LiveData<AddRssResponseModel> addRss(AddRssRequestModel request) {
        final MutableLiveData<AddRssResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), request.getUserId());
        RequestBody feedName = RequestBody.create(MediaType.parse("multipart/form-data"), request.getFeedName());
        RequestBody link = RequestBody.create(MediaType.parse("multipart/form-data"), request.getLink());


        Log.d("SearchRequest", s);
        apiServices.addRss(userId,feedName,link).enqueue(new Callback<AddRssResponseModel>() {
            @Override
            public void onResponse(Call<AddRssResponseModel> call, Response<AddRssResponseModel> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<AddRssResponseModel> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }

    public LiveData<GetRssResponseModel> getRss(GetRssRequestModel request) {
        final MutableLiveData<GetRssResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), request.getUserId());


        Log.d("SearchRequest", s);
        apiServices.getRss(userId).enqueue(new Callback<GetRssResponseModel>() {
            @Override
            public void onResponse(Call<GetRssResponseModel> call, Response<GetRssResponseModel> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<GetRssResponseModel> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }

    public LiveData<EditRssResponseModel> editRss(EditRssRequestModel request) {
        final MutableLiveData<EditRssResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), request.getId());
        RequestBody feedName = RequestBody.create(MediaType.parse("multipart/form-data"), request.getFeedName());
        RequestBody link = RequestBody.create(MediaType.parse("multipart/form-data"), request.getLink());


        Log.d("SearchRequest", s);
        apiServices.editRss(id,feedName,link).enqueue(new Callback<EditRssResponseModel>() {
            @Override
            public void onResponse(Call<EditRssResponseModel> call, Response<EditRssResponseModel> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<EditRssResponseModel> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }

    public LiveData<DeleteRssResponseModel> deleteRss(DeleteRssRequestModel request) {
        final MutableLiveData<DeleteRssResponseModel> data =  new MutableLiveData();

        String s = new Gson().toJson(request);
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), request.getId());


        Log.d("SearchRequest", s);
        apiServices.deleteRss(userId).enqueue(new Callback<DeleteRssResponseModel>() {
            @Override
            public void onResponse(Call<DeleteRssResponseModel> call, Response<DeleteRssResponseModel> response) {
                data.postValue(response.body());
                String s = new Gson().toJson(response.body());

                Log.d("SearchResponse", s);
            }

            @Override
            public void onFailure(Call<DeleteRssResponseModel> call, Throwable t) {
                String s = new Gson().toJson(t);

                Log.d("SearchResponse", s);
            }
        });

        return data;
    }


}

