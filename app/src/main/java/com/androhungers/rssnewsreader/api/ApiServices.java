package com.androhungers.rssnewsreader.api;

import com.androhungers.rssnewsreader.model.signin.SignRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {


    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/login_user")
    Call<SigninResponseModel> signInUser(@Part("user_name") RequestBody username,@Part("password") RequestBody password);


}
