package com.androhungers.rssnewsreader.model.addRss;

public class AddRssRequestModel {
    private String userId = null;
    private String feedName = null;
    private String link = null;

    public AddRssRequestModel(String userId, String feedName, String link) {
        this.userId = userId;
        this.feedName = feedName;
        this.link = link;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
