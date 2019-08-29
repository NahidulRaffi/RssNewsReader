package com.androhungers.rssnewsreader.model.getRss;

public class GetRssRequestModel {
    private String userId = null;

    public GetRssRequestModel(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
