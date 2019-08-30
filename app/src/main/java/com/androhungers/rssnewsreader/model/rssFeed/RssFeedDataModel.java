package com.androhungers.rssnewsreader.model.rssFeed;

public class RssFeedDataModel {
    FeedItem feedItem;
    String title;

    public RssFeedDataModel(FeedItem feedItem, String title) {
        this.feedItem = feedItem;
        this.title = title;
    }

    public FeedItem getFeedItem() {
        return feedItem;
    }

    public void setFeedItem(FeedItem feedItem) {
        this.feedItem = feedItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
