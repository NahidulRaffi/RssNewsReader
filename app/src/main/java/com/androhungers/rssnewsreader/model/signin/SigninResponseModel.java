package com.androhungers.rssnewsreader.model.signin;

import com.androhungers.rssnewsreader.model.signin.DataModel;

public class SigninResponseModel {
    private boolean success = false;
    private DataModel data = null;

    public SigninResponseModel() {
    }

    public SigninResponseModel(boolean success, DataModel data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }
}
