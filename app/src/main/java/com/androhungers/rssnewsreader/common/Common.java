package com.androhungers.rssnewsreader.common;

import android.util.Log;

import com.androhungers.rssnewsreader.model.signin.DataModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Common {
    public DataModel getUserDataFromSignIn(String data){
        String s1 = new Gson().toJson(data);
        String s = s1.substring(1, s1.length()-1);

        String temp = s.replace("\\", "");
        Log.d(">>>>",temp);

        Gson gson = new Gson();

        Type type = new TypeToken<DataModel>() {
        }.getType();

        DataModel dataModel = gson.fromJson(temp, type);

        return dataModel;
    }
}
