package com.androhungers.rssnewsreader.api;

import com.androhungers.rssnewsreader.model.addRss.AddRssResponseModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssResponseModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssResponseModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssResponseModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.androhungers.rssnewsreader.model.signup.SignupResponseModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {


    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/login_user")
    Call<SigninResponseModel> signInUser(@Part("user_name") RequestBody username,@Part("password") RequestBody password);

    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/register_user")
    Call<SignupResponseModel> signUpUser(
            @Part("user_name") RequestBody username,
            @Part("password") RequestBody password,
            @Part("name") RequestBody name,
            @Part("age") RequestBody age
            );

    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/add_rss_feed")
    Call<AddRssResponseModel> addRss(
            @Part("user_id") RequestBody userId,
            @Part("feed_name") RequestBody feedName,
            @Part("link") RequestBody link
    );

    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/get_rss_feed")
    Call<GetRssResponseModel> getRss(
            @Part("user_id") RequestBody userId
    );

    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/edit_rss_feed")
    Call<EditRssResponseModel> editRss(
            @Part("id") RequestBody id,
            @Part("feed_name") RequestBody feedName,
            @Part("link") RequestBody link
    );

    @Headers("Authorization: pGYBd52nZZrR_7UqpxmKD2_A9ZX2QAzRSr")
    @Multipart
    @POST("admi_co/v2/delete_rss_feed")
    Call<DeleteRssResponseModel> deleteRss(
            @Part("id") RequestBody id
    );



}
