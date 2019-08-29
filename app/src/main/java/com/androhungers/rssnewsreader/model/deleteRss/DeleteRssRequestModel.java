package com.androhungers.rssnewsreader.model.deleteRss;

public class DeleteRssRequestModel {
    private String id = null;


    public DeleteRssRequestModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
