package com.androhungers.rssnewsreader.api;

import com.androhungers.rssnewsreader.model.signin.SignRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {


    @POST("User/RegisterUser/")
    Call<SigninResponseModel> signInUser(@Body SignRequestModel signRequestModel);


}
