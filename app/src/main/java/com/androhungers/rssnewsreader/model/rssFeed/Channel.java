package com.androhungers.rssnewsreader.model.rssFeed;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "channel", strict = false)
public class Channel implements Serializable {
    @ElementList(inline = true, name="item", required = false)
    private List<FeedItem> mFeedItems;

    @Element(name="title")
    private String mTitle;

    public Channel() {
    }

    public Channel(List<FeedItem> mFeedItems, String mTitle) {
        this.mFeedItems = mFeedItems;
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public List<FeedItem> getmFeedItems() {
        return mFeedItems;
    }

    public void setmFeedItems(List<FeedItem> mFeedItems) {
        this.mFeedItems = mFeedItems;
    }
}
