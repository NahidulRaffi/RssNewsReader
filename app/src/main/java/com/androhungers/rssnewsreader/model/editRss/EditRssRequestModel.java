package com.androhungers.rssnewsreader.model.editRss;

public class EditRssRequestModel {
    private String id = null;
    private String feedName = null;
    private String link = null;

    public EditRssRequestModel(String id, String feedName, String link) {
        this.id = id;
        this.feedName = feedName;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
